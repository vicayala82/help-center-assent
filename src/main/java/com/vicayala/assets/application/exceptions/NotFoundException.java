package com.vicayala.assets.application.exceptions;

public class NotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "Not Found Item in %s";

    public NotFoundException(String serviceName){
        super(String.format(ERROR_MESSAGE, serviceName));
    }
}
