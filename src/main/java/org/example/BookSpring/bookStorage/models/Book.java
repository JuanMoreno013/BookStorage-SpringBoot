package org.example.BookSpring.bookStorage.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.BookSpring.bookStorage.validators.ChainValidator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.time.LocalDate;
@Entity
@Table(name = "Books")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@NamedQuery(query = "select u from Book u", name = "query_find_all_books")
public final class Book extends ItemOp {

    @Column(name = "nsbn")
    private String nsbn;
    @Column(name = "subject")
    private String subject;
    @Column(name = "status")
    private String status;
    @Column(name = "editorial")
    private String editorial;

    @Column(name = "user_taken")
    private  Integer user_taken;

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
                        "\n User taken: " + getUser_taken();
    }

}
