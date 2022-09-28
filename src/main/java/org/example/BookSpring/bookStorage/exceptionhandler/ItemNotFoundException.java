package org.example.BookSpring.bookStorage.exceptionhandler;


public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message){
        super(message);
    }
}