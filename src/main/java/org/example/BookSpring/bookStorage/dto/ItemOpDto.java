package org.example.BookSpring.bookStorage.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public abstract class ItemOpDto {

    private int id;
    private  String title;
    private  String author;
    private  int pages;
    private LocalDate date_write;

    private static ChainValidator validateChain = new ChainValidator();

    public ItemOpDto(String title, String author, int pages, LocalDate date_write) {

        this.title = validateChain.processValidator(title);
        this.author = validateChain.processValidator(author);
        this.pages = validateChain.processValidator(pages);
        this.date_write = validateChain.processValidator(date_write);
    }

    public String toString() {
        return
                "\n Title: " + getTitle() +
                        "\n Author: " + getAuthor() +
                        "\n Pages: " + getPages() +
                        "\n Date: " + getDate_write();
    }
}
