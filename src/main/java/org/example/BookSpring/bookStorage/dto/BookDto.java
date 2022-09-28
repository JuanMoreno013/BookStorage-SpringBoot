package org.example.BookSpring.bookStorage.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class BookDto extends ItemOpDto {
    private String nsbn;
    private String subject;
    private String status;
    private String editorial;

    private Integer user_taken;

    private static ChainValidator validateChain = new ChainValidator();

    public BookDto(String title, String author, int pages, LocalDate date_write, String subject, String nsbn, String status, String editorial) {

        super(title, author, pages, date_write);
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
                        "\n User taken: " + getUser_taken();
    }

}
