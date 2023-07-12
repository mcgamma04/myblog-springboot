package com.stringcodeltd.myblogapp.exception;

public class CategoryAleadyExistException extends RuntimeException{
    private String resourceName;
    private String fleldName;
    private String fieldValue;

    public CategoryAleadyExistException(String resourceName, String fleldName, String fieldValue) {
        super(String.format("%s  found with %s: %s",resourceName,fleldName,fieldValue));//post not found with id:1
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

