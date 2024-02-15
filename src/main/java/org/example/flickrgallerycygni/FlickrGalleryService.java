package org.example.flickrgallerycygni;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// Serviceklass som hanterar logiken för att hämta bilder från Flickr API
@Service
public class FlickrGalleryService {

    // Metod som hämtar bilder från Flickr API
    // Skickar med en söksträng och ett sidnummer
    public String getFlickrAPI(String text, int page) {

        // Försöker att hämta bilder från Flickr API
        try {

            // Skapar ett objekt av klassen FlickrAPI och definierar parametrar och URL
            FlickrAPI flickrAPIobj = new FlickrAPI("https://www.flickr.com/services/rest/?",
                    "89a7f2ad2f8260a035683e01e3e62a71",
                    "flickr.photos.search",
                    "json",
                    text,
                    page,
                    10);

            // Skapar ett objekt av klassen RestTemplate för att göra en GET-förfrågan
            RestTemplate restTemplate = new RestTemplate();

            // Skapar en URL-sträng med parametrar för att göra en GET-förfrågan till Flickr API
            String url = flickrAPIobj.getUrlEndpoint() + "format=" + flickrAPIobj.getFormat() + "&text=" + flickrAPIobj.text
                    + "&api_key=" + flickrAPIobj.getApiKey() + "&method=" + flickrAPIobj.getMethod() + "&nojsoncallback=1"
                    + "&page=" + flickrAPIobj.page + "&per_page=" + flickrAPIobj.perPage;

            // Gör en GET-förfrågan till Flickr API och sparar svaret i en sträng
            String response = restTemplate.getForObject(url, String.class);

            // Skriver ut responsen
            System.out.println(response);

            // returnerar svaret som är en JSON-sträng med bilder
            return response;

            // Fångar upp eventuella fel och skriver ut dem
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
