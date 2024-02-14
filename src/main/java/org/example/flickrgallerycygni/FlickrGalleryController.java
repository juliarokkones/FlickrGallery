package org.example.flickrgallerycygni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlickrGalleryController {

    @Autowired
    FlickrGalleryService flickrGalleryService;

    @Autowired
    public FlickrGalleryController(FlickrGalleryService flickrGalleryService) {
        this.flickrGalleryService = flickrGalleryService;
    }

    @GetMapping ("/json")
    public String getImages (@RequestParam String text, @RequestParam(defaultValue = "1") int page) {
        return flickrGalleryService.getImages(text, page);
    }

}
