package com.example.m02s10.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message){
        super(message);
    }
    public BadRequestException(){
        super();
    }

}
