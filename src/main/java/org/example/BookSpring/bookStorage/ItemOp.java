package org.example.BookSpring.bookStorage;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.Validator.ChainValidator;

import java.time.LocalDate;

@Getter
@Setter
public abstract class ItemOp {

    private int id;
    private final String title;
    private final String author;
    private final int pages;
    private final LocalDate dateWrite;

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
}
