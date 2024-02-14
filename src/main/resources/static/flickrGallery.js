const API_BASE_URL = "http://localhost:3000";
const PAGE_SIZE = 5;
let currentPage = 1;
let isLoading = false;

document.getElementById("search-button").addEventListener("click", () => {
    const textInput = document.getElementById("text-input").value;
    if (!textInput) {
        alert("Vänligen skriv in text");
        return;
    }
    currentPage = 1;
    fetchImages(textInput).then(r => console.log(r) );
});

window.addEventListener("scroll", () => {
    if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
        if (!isLoading) {
            isLoading = true;
            currentPage++;
            const textInput = document.getElementById("text-input").value;
            fetchImages(textInput, currentPage).then(r => console.log(r));
        }
    }
});

async function fetchImages(textInput, page) {
    const url = `${API_BASE_URL}/json?text=${encodeURIComponent(textInput)}&per_page=${PAGE_SIZE}&page=${page}`;


    showLoading(true);

    console.log(url);
    if (!textInput) {
        alert("Vänligen skriv in text");
        return;
    }

    try {
        const response = await fetch(url);
        console.log(response);
        if (response.ok) {
            const data = await response.json();
            displayImages(data);
        } else {
            alert("Misslyckades att hämta data från servern");
            console.error(`Failed to fetch data. Status: ${response.status}`);
        }
    } catch (error) {
        console.error('Error:', error);
        showError(true);
    }
    showLoading(false);
}

function displayImages(rsp){
    if (rsp.stat != "ok"){
        // something broke!
        console.error(rsp.message);
        return;
    }

    const imagesContainer = document.getElementById("images-container");
    imagesContainer.innerHTML = "";
    for (var i=0; i<rsp.photos.photo.length; i++){
        var photo = rsp.photos.photo[i];
        var img = document.createElement('img');
        img.src = `https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg`;
        imagesContainer.appendChild(img);
    }
}

function showLoading(isLoading) {
    const loading = document.getElementById("loading");
    loading.classList.toggle("hidden", !isLoading);
}

function showError(hasError) {
    const error = document.getElementById("error");
    error.classList.toggle("hidden", !hasError);
}