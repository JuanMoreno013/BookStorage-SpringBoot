package org.example.BookSpring.bookStorage.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class LetterDto extends ItemOpDto {
    private String subject;
    private String place;

    private Integer user_taken;

    private static ChainValidator validateChain = new ChainValidator();

    public LetterDto(String title, String author, int pages, LocalDate date_write, String subject, String place) {
        super(title, author, pages, date_write);

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
