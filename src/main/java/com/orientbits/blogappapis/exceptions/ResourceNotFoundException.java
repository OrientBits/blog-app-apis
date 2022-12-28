package com.orientbits.blogappapis.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String filedName;
    Integer fieldValue;

    public ResourceNotFoundException(String resourceName, String filedName, Integer fieldValue) {
        super(""+resourceName+" not found with "+filedName+" : "+fieldValue);
        this.resourceName = resourceName;
        this.filedName = filedName;
        this.fieldValue = fieldValue;
    }
}
