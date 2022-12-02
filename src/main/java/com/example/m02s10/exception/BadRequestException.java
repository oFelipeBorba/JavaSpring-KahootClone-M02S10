package com.example.m02s10.exception;

public class BadRequestException extends RuntimeException {

    BadRequestException(String message){
        super(message);
    }
    BadRequestException(){
        super();
    }

}
