package org.example.BookSpring.bookStorage.Vexceptions;

public class ItemBadRequestException extends RuntimeException{

    public ItemBadRequestException(String message){
        super(message);
    }
}
