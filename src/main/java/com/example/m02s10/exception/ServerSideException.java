package com.example.m02s10.exception;


public class ServerSideException extends RuntimeException {

    ServerSideException(String message){
        super(message);
    }
    ServerSideException(){
        super();
    }

}