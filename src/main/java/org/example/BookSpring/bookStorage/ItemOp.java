package org.example.BookSpring.bookStorage;

import lombok.Getter;
import lombok.Setter;



import java.time.LocalDate;


@Getter
@Setter
public abstract class ItemOp {

    private int id ;
    private  String title;
    private  String author;
    private  int pages;
    private  LocalDate dateWrite;

//    private static int prevId = 1;

    private static int nextId = 1;

    public ItemOp(String title, String author , int pages, LocalDate dateWrite){

        validate(title, author, pages, dateWrite);
        this.id = nextId++;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.dateWrite = dateWrite;
    }



     public void ValidationP(Object... arrayOfObj){
        for (Object obj: arrayOfObj) {
            if (obj == null) {
                throw new IllegalArgumentException();
            }
        }
     }
    private void validate(String title, String author , int pages, LocalDate dateWrite) {
        ValidationP(title, author, dateWrite);

        if (title.isBlank())
            throw new IllegalArgumentException(" Blank title");

        if (author.isBlank())
            throw new IllegalArgumentException(" Author Blank");

        if (dateWrite.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date wrong");

        if (pages < 1)
            throw new IllegalArgumentException( "Error Pages! ");
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
