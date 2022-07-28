package org.example.BookSpring.bookStorage.Magazines;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Validator.ChainValidator;

import java.time.LocalDate;

@Getter
@Setter
public class Magazine extends ItemOp {
    private final int volume;
    private final String subject;
    private final String editorial;
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
