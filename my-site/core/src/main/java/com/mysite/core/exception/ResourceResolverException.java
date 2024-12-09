package com.mysite.core.exception;

public class ResourceResolverException extends RuntimeException{
    public ResourceResolverException(String message){
        super(message);
    }

    public ResourceResolverException(String message, Throwable cause){
        super(message, cause);
    }
}
