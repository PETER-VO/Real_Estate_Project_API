package com.phongvo.estatespringboot.exceptions;

public class BuildingNotFoundException extends RuntimeException {
    public BuildingNotFoundException(String exMessage) {
        super(exMessage);
    }
}
