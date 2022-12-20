package com.olusegun.exceptions;

public class CartItemNotExistException extends IllegalStateException{

    public CartItemNotExistException(String message){
        super(message);
    }
}
