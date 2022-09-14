package org.example.BookSpring.bookStorage.Letters;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Validator.ChainValidator;

import java.time.LocalDate;


@Getter
@Setter
public final class Letter extends ItemOp {
    private final String subject;
    private final String place;

    private  Integer userTaken;

    private static ChainValidator validateChain = new ChainValidator();

    public Letter(String title, String author, int pages, LocalDate dateWrite, String subject, String place) {
        super(title, author, pages, dateWrite);

        this.subject = validateChain.processValidator(subject);
        this.place = validateChain.processValidator(place);
    }

    public String toString() {
        return
                super.toString() +
                        "\n Subject: " + getSubject() +
                        "\n Place: " + getPlace();
    }
}
