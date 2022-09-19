package org.example.BookSpring.bookStorage.models;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import java.time.LocalDate;

@Getter
@Setter
public final class Book extends ItemOp {

    private String nsbn;
    private String subject;
    private String status;
    private String editorial;

    private  Integer userTaken;

    private static ChainValidator validateChain = new ChainValidator();

    public Book(String title, String author, int pages, LocalDate dateWrite, String subject, String nsbn,  String status, String editorial) {

        super(title, author, pages, dateWrite);
        this.subject = validateChain.processValidator(subject);
        this.nsbn = validateChain.processValidator(nsbn);
        this.editorial = validateChain.processValidator(editorial);
        this.status = validateChain.processValidator(status);
    }

    public String toString() {
        return
                super.toString() +
                        "\n Subject: " + getSubject() +
                        "\n ISBN: " + getNsbn() +
                        "\n Editorial: " + getEditorial() +
                        "\n Status: " + getStatus() +
                        "\n User taken: " + getUserTaken();
    }

}
