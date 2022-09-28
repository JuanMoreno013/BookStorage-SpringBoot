package org.example.BookSpring.bookStorage.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MagazineDto extends ItemOpDto {
    private int volume;
    private String subject;
    private String editorial;

    private Integer user_taken;

    private static ChainValidator validateChain = new ChainValidator();

    public MagazineDto(String title, String author, int pages, LocalDate date_write, String subject, int volume, String editorial) {
        super(title, author, pages, date_write);
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
