package org.example.BookSpring.bookStorage;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.Validator.ChainValidator;
import org.example.BookSpring.bookStorage.Validator.Validator;


import java.time.LocalDate;


@Getter
@Setter
public abstract class ItemOp {

    private int id ;
    private  String title;
    private  String author;
    private  int pages;
    private  LocalDate dateWrite;

    private static  ChainValidator validateChain = new ChainValidator();
    private static int nextId = 1;

    public ItemOp(String title, String author , int pages, LocalDate dateWrite){

        this.id = nextId++;
        this.title = validateChain.processValidator(title);
        this.author = validateChain.processValidator(author);
        this.pages = validateChain.processValidator(pages);
        this.dateWrite = validateChain.processValidator(dateWrite);

    }
    public String toString() {
        return
                "\n Id: " + getId() +
                "\n Title: " + getTitle() +
                "\n Author: " + getAuthor() +
                "\n Pages: " + getPages() +
                "\n Date: " + getDateWrite() ;
    }
}
