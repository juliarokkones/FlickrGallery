package org.example.flickrgallerycygni;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FlickrGalleryService {

    public String getImages(String text, int page) {
        String apiKey = "89a7f2ad2f8260a035683e01e3e62a71";
        String method = "flickr.photos.search";
        String format = "json";
        String perPage = "10";

        String url = "https://www.flickr.com/services/rest/?format=" + format + "&text=" + text
                + "&api_key=" + apiKey + "&method=" + method + "&nojsoncallback=1"
                + "&page=" + page + "&per_page=" + perPage;

        RestTemplate restTemplate = new RestTemplate();

        // Make a GET request to the Flickr API
        String response = restTemplate.getForObject(url, String.class);

        // Check if the response is valid JSON
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);

            // Do something with the JSON response
            System.out.println(jsonNode.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }
}
