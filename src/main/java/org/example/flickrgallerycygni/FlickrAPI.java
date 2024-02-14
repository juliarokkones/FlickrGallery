package org.example.flickrgallerycygni;

public class FlickrAPI {

    String urlEndpoint;
    String apiKey;
    String method;
    String format;
    String text;

    public FlickrAPI(String urlEndpoint, String apiKey, String method, String format, String text) {
        this.urlEndpoint = urlEndpoint;
        this.apiKey = apiKey;
        this.method = method;
        this.format = format;
        this.text = text;
    }

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

}
