package org.example.BookSpring.bookStorage.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class ItemOp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private  String title;
    @Column(name = "author")
    private  String author;
    @Column(name = "pages")
    private  int pages;
    @Column(name = "date_write")
    private  LocalDate date_write;

    private static ChainValidator validateChain = new ChainValidator();

    public ItemOp(String title, String author, int pages, LocalDate date_write) {

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
