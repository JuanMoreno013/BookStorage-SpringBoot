package org.example.BookSpring.bookStorage.Validator;

import org.example.BookSpring.bookStorage.ItemOp;
import org.example.BookSpring.bookStorage.Magazines.Magazine;

import java.awt.print.Book;

public class NotBlank<T> extends Validator<T>{

    protected NotBlank(Validator<T> nextValidation) {

        super(nextValidation);
    }

    @Override
    public void processValidation(T request) {

        if ((request.toString()).isBlank())
            throw new IllegalArgumentException(" Something is Blank! ");

        super.processValidation(request);

    }
}
