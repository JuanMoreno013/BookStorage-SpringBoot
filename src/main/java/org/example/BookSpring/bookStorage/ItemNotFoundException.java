package org.example.BookSpring.bookStorage;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(int id){
        super("Could not find that item " +id);
    }
}