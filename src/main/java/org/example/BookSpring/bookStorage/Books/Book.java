package org.example.BookSpring.bookStorage.Books;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Validator.ChainValidator;


import java.time.LocalDate;

@Getter
@Setter
public class Book extends ItemOp {


    private final String nsbn;
    private final String subject;
    private final String status;
    private final String editorial;

    private static  ChainValidator validateChain = new ChainValidator();

    public Book(String title, String author, int pages, LocalDate dateWrite, String subject, String nsbn, String editorial, String status) {

        super(title, author, pages, dateWrite);

        this.subject= validateChain.processValidator(subject);
        this.nsbn= validateChain.processValidator(nsbn);
        this.editorial= validateChain.processValidator(editorial);
        this.status= validateChain.processValidator(status);
    }

    public String toString() {
        return
                super.toString()+
                "\n Subject: " + getSubject() +
                "\n ISBN: " + getNsbn() +
                "\n Editorial: " + getEditorial() +
                "\n Status: " + getStatus();
    }
}
