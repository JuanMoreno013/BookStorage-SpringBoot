package org.example.BookSpring.bookStorage.models;

import lombok.*;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Letters")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@NamedQuery(query = "select u from Letter u", name = "query_find_all_letters")
public final class Letter extends ItemOp {
    @Column(name = "subject")
    private String subject;
    @Column(name = "place")
    private String place;

    @Column(name = "user_taken")
    private Integer user_taken;

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
