package org.example.BookSpring.bookStorage.models;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import java.time.LocalDate;

@Getter
@Setter
public abstract class ItemOp {

    private int id;
    private  String title;
    private  String author;
    private  int pages;
    private  LocalDate dateWrite;

    private static ChainValidator validateChain = new ChainValidator();

    public ItemOp(String title, String author, int pages, LocalDate dateWrite) {

        this.title = validateChain.processValidator(title);
        this.author = validateChain.processValidator(author);
        this.pages = validateChain.processValidator(pages);
        this.dateWrite = validateChain.processValidator(dateWrite);
    }

    public String toString() {
        return
                "\n Title: " + getTitle() +
                        "\n Author: " + getAuthor() +
                        "\n Pages: " + getPages() +
                        "\n Date: " + getDateWrite();
    }
//
//    public void setDateWrite(LocalDate dateWrite) {
//        this.dateWrite = validateChain.processValidator(dateWrite);
//    }

}
