package com.phongvo.estatespringboot.exceptions;

public class SpringNotifiException extends RuntimeException{
    public SpringNotifiException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringNotifiException(String exMessage) {
        super(exMessage);
    }
}
