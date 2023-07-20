package com.stringcodeltd.myblogapp.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException{

    private HttpStatus status;
    private String messages;

    public BlogApiException(HttpStatus status, String messages) {
        this.status = status;
        this.messages = messages;
    }

    public BlogApiException(String message, HttpStatus status, String messages1) {
        super(message);
        this.status = status;
        this.messages = messages1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
