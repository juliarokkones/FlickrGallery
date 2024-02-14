// API Base URL - serveradressen
const API_BASE_URL = "http://localhost:3000";

// Set global variables
let currentPage = 1;
let totalPages = 0;
let perPage = 10;


document.getElementById("search-button").addEventListener("click", () => {
    const textInput = document.getElementById("text-input").value;
    if (!textInput) {
        alert("Vänligen skriv in text");
        return;
    }
    currentPage = 1;
    fetchImages(currentPage).then(r => console.log(r) );
});

async function fetchImages(currentPage) {
    const textInput = document.getElementById("text-input").value;
    const url = `${API_BASE_URL}/json?text=${encodeURIComponent(textInput)}&page=${currentPage}&perPage=${perPage}`;

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
            totalPages = data.photos.pages;
            displayImages(data);
            renderPagination();
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

function displayImages(rsp) {
    if (rsp.stat != "ok") {
        // something broke!
        console.error(rsp.message);
        return;
    }

    const imagesContainer = document.getElementById("images-container");
    imagesContainer.innerHTML = "";
    for (var i = 0; i < rsp.photos.photo.length; i++) {
        var photo = rsp.photos.photo[i];
        var img = document.createElement('img');
        img.src = `https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg`;
        imagesContainer.appendChild(img);
    }
}

function renderPagination() {
    const paginationContainer = document.getElementById("pagination");
    paginationContainer.innerHTML = "";
    const prevButton = document.createElement("button");
    prevButton.innerHTML = "Previous";
    prevButton.onclick = function () {
        if (currentPage > 1) {
            currentPage--;
            fetchImages().then(r => console.log(r));
        }
    };
    paginationContainer.appendChild(prevButton);

    const nextButton = document.createElement("button");
    nextButton.innerHTML = "Next";
    nextButton.onclick = function () {
        if (currentPage < totalPages) {
            currentPage++;
            fetchImages(currentPage).then(r => console.log(r)) ;
        }
    };
    paginationContainer.appendChild(nextButton);

    const currentPageSpan = document.createElement("span");
    currentPageSpan.innerHTML = `Page ${currentPage} of ${totalPages}`;
    paginationContainer.appendChild(currentPageSpan);
}



function showLoading(isLoading) {
    const loading = document.getElementById("loading");
    loading.classList.toggle("hidden", !isLoading);
}

function showError(hasError) {
    const error = document.getElementById("error");
    error.classList.toggle("hidden", !hasError);
}