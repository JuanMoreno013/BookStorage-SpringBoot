package org.example.BookSpring.bookStorage.models;

import lombok.*;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Magazines")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@NamedQuery(query = "select u from Magazine u", name = "query_find_all_magazines")
public final class Magazine extends ItemOp {

    @Column(name = "volume")
    private int volume;
    @Column(name = "subject")
    private String subject;
    @Column(name = "editorial")
    private String editorial;

    @Column(name = "user_taken")
    private Integer user_taken;

    private static ChainValidator validateChain = new ChainValidator();

    public Magazine(String title, String author, int pages, LocalDate date_write, String subject, int volume, String editorial) {
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
