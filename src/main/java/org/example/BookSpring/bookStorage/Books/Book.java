package org.example.BookSpring.bookStorage.Books;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.ItemOp;


import java.time.LocalDate;

@Getter
@Setter
public class Book extends ItemOp {


    private final String nsbn;
    private final String subject;
    private final String status;
    private final String editorial;





    public Book(String title, String author, int pages, LocalDate dateWrite, String subject, String nsbn, String editorial, String status) {

        super(title, author, pages, dateWrite);
        validate(nsbn,subject,status,editorial);

        this.subject= subject;
        this.nsbn= nsbn;
        this.editorial= editorial;
        this.status= status;
    }



    public void ValidationP(Object... arrayOfObj){
        for (Object obj: arrayOfObj) {
            if (obj == null) {
                throw new IllegalArgumentException();
            }
        }
    }
    private void validate(String subject, String id, String editorial, String status) {
        ValidationP(id, subject, status, editorial);

        if (subject.isBlank())
            throw new IllegalArgumentException("Book: Blank Subject");

        if (status.isBlank())
            throw new IllegalArgumentException("Book: Status is Blank");

        if (id.isBlank())
            throw new IllegalArgumentException("Book: ISBN es Blank ");

        if (editorial.isBlank())
            throw new IllegalArgumentException("Book: Editorial is Blank");
    }



    public String toString() {
        return

                super.toString()+
                "\n Subject: " + getSubject() +
                "\n ISBN: " + getNsbn() +
                "\n Editorial: " + getEditorial() +
                "\n Status: " + getStatus();
    }
}
