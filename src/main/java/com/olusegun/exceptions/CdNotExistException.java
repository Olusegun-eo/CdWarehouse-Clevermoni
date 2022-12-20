package com.olusegun.exceptions;

public class CdNotExistException extends IllegalArgumentException{
    public CdNotExistException(String message){
        super(message);
    }
}
