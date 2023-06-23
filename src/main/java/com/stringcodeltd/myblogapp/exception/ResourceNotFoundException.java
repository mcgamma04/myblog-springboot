package com.stringcodeltd.myblogapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fleldName;
    private Long fieldValue;

    public ResourceNotFoundException(String resourceName, String fleldName, Long fieldValue) {
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

    public Long getFieldValue() {
        return fieldValue;
    }
}
