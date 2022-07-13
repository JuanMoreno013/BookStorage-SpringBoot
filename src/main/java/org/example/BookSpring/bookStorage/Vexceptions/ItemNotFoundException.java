package org.example.BookSpring.bookStorage.Vexceptions;


public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(String message){
        super(message);
    }
}