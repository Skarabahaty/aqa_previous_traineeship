package models;

import kong.unirest.HttpResponse;

public class Response {

    public static String CONTENT_TYPE = "Content-Type";

    public <T> Response(HttpResponse<T> httpResponse) {
        this.status = httpResponse.getStatus();
        this.contentType = httpResponse.getHeaders().getFirst(CONTENT_TYPE);
        this.body = httpResponse.getBody();
    }

    private final int status;
    private final String contentType;
    private final Object body;

    public int getStatus() {
        return status;
    }

    public String getContentType() {
        return contentType;
    }

    public Object getBody() {
        return body;
    }
}
