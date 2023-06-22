package com.stringcodeltd.myblogapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fleldName;
    private String fieldValue;

    public ResourceNotFoundException(String resourceName, String fleldName, String fieldValue) {
        super(String.format("%s not found with %s: %s",resourceName,fleldName,fieldValue));//post not found with id:1
        this.resourceName = resourceName;
        this.fleldName = fleldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFleldName() {
        return fleldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
