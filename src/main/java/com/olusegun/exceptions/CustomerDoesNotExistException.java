package com.olusegun.exceptions;

public class CustomerDoesNotExistException extends IllegalArgumentException{
    public CustomerDoesNotExistException(String message){
        super(message);
    }
}
