package com.edielson.forum.services.exceptions;

public class DataBaseException extends RuntimeException {
    
    public DataBaseException(String msg) {
        super(msg);
    }
}