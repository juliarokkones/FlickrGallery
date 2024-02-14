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

    public String getImages(String text) {
        String apiKey = "89a7f2ad2f8260a035683e01e3e62a71";
        String method = "flickr.photos.search";
        String format = "json";

        String url = "https://www.flickr.com/services/rest/?format=" + format + "&text=" + text + "&api_key=" + apiKey + "&method=" + method + "&nojsoncallback=1";

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

    public FlickrAPI testtest(String text) {

        // https://www.flickr.com/services/rest/?format=json&text=tree&api_key=89a7f2ad2f8260a035683e01e3e62a71&nojsoncallback=1&method=flickr.photos.search
        FlickrAPI flickrAPI = new FlickrAPI("https://www.flickr.com/services/rest/?", "89a7f2ad2f8260a035683e01e3e62a71", "flickr.photos.search", "json", text);


        String urlEndPoint = "https://www.flickr.com/services/rest/?";
        String apiKey = "api_key=89a7f2ad2f8260a035683e01e3e62a71&";
        String method = "method=flickr.photos.search&";
        String format = "format=json&";
        text= "text=" + text;

        String url = urlEndPoint + apiKey + method + format;



       // format=" + format + "&text=" + text + "&api_key=" + apiKey + "&method=" + method + "/";
        // nojsoncallback=1


        RestTemplate restTemplate = new RestTemplate(); // Skapa en RestTemplate

// Lägg till en message converter för att kunna hantera JSON på rätt sätt
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);

        // Gör en GET-begäran till den externa API:et med den dynamiska URL:en
        FlickrAPI result = restTemplate.getForObject(url, FlickrAPI.class);
        System.out.println(result);
        return result;
    }
}
