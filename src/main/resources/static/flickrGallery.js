// API Base URL - serveradressen
const API_BASE_URL = "http://localhost:3000";

async function fetchImages() {
    const textInput = document.getElementById("text-input").value;
    const url = `${API_BASE_URL}/json?text=${encodeURIComponent(textInput)}`;

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
            jsonFlickrApi(data);
        } else {
            alert("Misslyckades att hämta data från servern");
            console.error(`Failed to fetch data. Status: ${response.status}`);
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

function jsonFlickrApi(rsp){
    if (rsp.stat != "ok"){
        // something broke!
        console.error(rsp.message);
        return;
    }

    for (var i=0; i<rsp.photos.photo.length; i++){
        var photo = rsp.photos.photo[i];
        var img = document.createElement('img');
        img.src = `https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg`;
        document.body.appendChild(img);
    }
}
