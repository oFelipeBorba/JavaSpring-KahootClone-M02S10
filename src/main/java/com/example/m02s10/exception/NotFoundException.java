package com.example.m02s10.exception;


public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(){
        super();
    }

}
