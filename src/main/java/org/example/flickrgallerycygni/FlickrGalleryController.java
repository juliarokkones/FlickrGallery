package org.example.flickrgallerycygni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Controllerklass som hanterar kopplingen till Flickr API genom REST
@RestController
public class FlickrGalleryController {

    // Instanserar FlickrGalleryService
    @Autowired
    FlickrGalleryService flickrGalleryService;

    // Konstruktor som tar emot en instans av FlickrGalleryService
    @Autowired
    public FlickrGalleryController(FlickrGalleryService flickrGalleryService) {
        this.flickrGalleryService = flickrGalleryService;
    }

    // Metod som hämtar bilder från Flickr API
    // Skickar med en söksträng och ett sidnummer
    // Anropar getImages-metoden i FlickrGalleryService
    @GetMapping ("/json")
    public String getImages (@RequestParam String text, @RequestParam(defaultValue = "1") int page) {
        return flickrGalleryService.getFlickrAPI(text, page);
    }

}
