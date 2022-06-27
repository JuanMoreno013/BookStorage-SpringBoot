package org.example.BookSpring.bookStorage.Magazines;

import lombok.Getter;
import lombok.Setter;
import org.example.BookSpring.bookStorage.ItemOp;

import java.time.LocalDate;



@Getter
@Setter
public class Magazine extends ItemOp {


    private final int volume;
    private final String subject;
    private final String editorial;

    public Magazine(String title, String author, int pages, LocalDate dateWrite, String subject, int volume, String editorial )
    {
        super(title, author, pages, dateWrite);
        validate(subject,volume, editorial);

        this.volume=volume;
        this.subject=subject;
        this.editorial=editorial;
    }

    public void ValidationP(Object... arrayOfObj){
        // array.foreach -> validate.
        //if (obj == null) ??
        for (Object obj: arrayOfObj) {
            if (obj == null) {
                throw new IllegalArgumentException();
            }
        }
    }
    private void validate(String subject, int volume, String editorial) {
        ValidationP(subject, volume, editorial);

        if (subject.isBlank())
            throw new IllegalArgumentException("Magazine: Blank Subject");

        if (editorial.isBlank())
            throw new IllegalArgumentException(" Magazine: Blank Editorial");

        if (volume < 1)
            throw new IllegalArgumentException("Magazine: Volume Less than 1");
    }

    public String toString() {
        return
                super.toString() +
                "\n Subject: " + getSubject() +
                "\n Volume: " + getVolume() +
                "\n Editorial: " + getEditorial();
    }

}
