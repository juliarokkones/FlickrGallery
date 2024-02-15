package org.example.flickrgallerycygni;

// Objektsklass som representerar en förfrågan till Flickr API
public class FlickrAPI {

    // Instansvariabler motsvarande ur och parametrar i Flickr API
    String urlEndpoint;
    String apiKey;
    String method;
    String format;
    String text;

    int page;

    int perPage;

    // Konstruktor
    public FlickrAPI(String urlEndpoint, String apiKey, String method, String format, String text, int page, int perPage) {
        this.urlEndpoint = urlEndpoint;
        this.apiKey = apiKey;
        this.method = method;
        this.format = format;
        this.text = text;
        this.page = page;
        this.perPage = perPage;
    }

    // Getters och setters
    public String getUrlEndpoint() {
        return urlEndpoint;
    }

    public void setUrlEndpoint(String urlEndpoint) {
        this.urlEndpoint = urlEndpoint;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

}
