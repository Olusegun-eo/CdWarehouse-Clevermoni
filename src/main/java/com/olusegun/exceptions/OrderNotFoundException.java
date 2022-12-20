package com.olusegun.exceptions;

public class OrderNotFoundException extends IllegalArgumentException{

    public OrderNotFoundException(String message){
        super(message);
    }
}
