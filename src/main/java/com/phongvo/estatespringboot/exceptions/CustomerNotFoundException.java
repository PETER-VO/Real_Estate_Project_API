package com.phongvo.estatespringboot.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String exMessage) {
        super(exMessage);
    }
}
