package org.example.BookSpring.bookStorage.exceptionhandler;

public class ItemBadRequestException extends RuntimeException{

    public ItemBadRequestException(String message){
        super(message);
    }
}
