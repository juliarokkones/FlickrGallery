// JavaScript-fil för att hämta bilder från servern och visa dem i en galleri
// Sätter API_BASE_URL till serverns URL med port 3000
const API_BASE_URL = "http://localhost:3000";

// Deklarerar variabler
let currentPage = 1;
let totalPages = 0;
let perPage = 10;

// Deklarera konstanter från HTML
const imagesContainer = document.getElementById("images-container");
const paginationContainer = document.getElementById("pagination");
const loading = document.getElementById("loading");

// Eventlistener för sökknappen som kallar på fetchImages
document.getElementById("search-button").addEventListener("click", () => {
    // Hämta texten från inputfältet och skickar alert om fältet är tomt
    const textInput = document.getElementById("text-input").value;
    if (!textInput) {
        alert("Vänligen skriv in text");
        return;
    }
    // Kallar på fetchImages och skickar med currentPage
    fetchImages(currentPage).then(r => console.log(r));
});

// Asyncron funktion som hämtar bilder från servern
async function fetchImages(currentPage) {
    // Hämtar texten från inputfältet och skapar en URL för att hämta bilder från servern med hjälp av texten, currentPage och perPage
    const textInput = document.getElementById("text-input").value;
    const url = `${API_BASE_URL}/json?text=${encodeURIComponent(textInput)}&page=${currentPage}&perPage=${perPage}`;

    // Visar laddning medan bilder hämtas
    showLoading(true);

    // Skickar en fetch request till servern och hämtar bilder
    try {
        const response = await fetch(url);

        // Om svaret är ok, hämtas data, totalPages och anropa görs till displayImages och renderPagination
        if (response.ok) {
            const data = await response.json();
            totalPages = data.photos.pages;
            displayImages(data);
            renderPagination();

            // Felmeddelande visas om svaret inte är ok
        } else {
            alert("Misslyckades att hämta data från servern");
            console.error(`Failed to fetch data. Status: ${response.status}`);
        }
    } catch (error) {
        console.error('Error:', error);
    }
    // Döljer laddning när bilder hämtats
    showLoading(false);
}

// Funktion för att visa bilder
function displayImages(rsp) {
    // Tömmer imagesContainer
    imagesContainer.innerHTML = "";

    // Loopar igenom bilderna och skapar img-element för varje bild och lägger till i imagesContainer
    for (var i = 0; i < rsp.photos.photo.length; i++) {
        var photo = rsp.photos.photo[i];
        var img = document.createElement('img');
        img.src = `https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg`;
        imagesContainer.appendChild(img);
    }
}

// Funktion för att skapa pagination
function renderPagination() {

    // Tömmer paginationContainer
    paginationContainer.innerHTML = "";

    // Skapar knappar för att bläddra mellan sidor och lägger till i paginationContainer
    // Vid klick på knapparna anropas fetchImages efter att currentPage uppdaterats
    // Bakåtknapp
    const prevButton = document.createElement("button");
    prevButton.innerHTML = "Previous";
    prevButton.onclick = function () {
        if (currentPage > 1) {
            currentPage--;
            fetchImages(currentPage).then(r => console.log(r));
        } else {
            alert ("You are on the first page");
        }
    };
    paginationContainer.appendChild(prevButton);

    // Framåtknapp
    const nextButton = document.createElement("button");
    nextButton.innerHTML = "Next";
    nextButton.onclick = function () {
        if (currentPage < totalPages) {
            currentPage++;
            fetchImages(currentPage).then(r => console.log(r));
        } else {
            alert ("You are on the last page");
        }
    };
    paginationContainer.appendChild(nextButton);

    // Skapar span för att visa vilken sida som visas och lägger till i paginationContainer
    const currentPageSpan = document.createElement("span");
    currentPageSpan.innerHTML = `Page ${currentPage} of ${totalPages}`;
    paginationContainer.appendChild(currentPageSpan);
}

// Funktion för att hantera laddning
function showLoading(isLoading) {
    loading.classList.toggle("hidden", !isLoading);
}
