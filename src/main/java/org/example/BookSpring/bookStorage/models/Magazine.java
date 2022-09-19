package org.example.BookSpring.bookStorage.models;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import java.time.LocalDate;

@Getter
@Setter
public final class Magazine extends ItemOp {
    private int volume;
    private String subject;
    private String editorial;

    private Integer userTaken;

    private static ChainValidator validateChain = new ChainValidator();

    public Magazine(String title, String author, int pages, LocalDate dateWrite, String subject, int volume, String editorial) {
        super(title, author, pages, dateWrite);
        this.volume = validateChain.processValidator(volume);
        this.subject = validateChain.processValidator(subject);
        this.editorial = validateChain.processValidator(editorial);

    }

    public String toString() {
        return
                super.toString() +
                        "\n Subject: " + getSubject() +
                        "\n Volume: " + getVolume() +
                        "\n Editorial: " + getEditorial();
    }

}
