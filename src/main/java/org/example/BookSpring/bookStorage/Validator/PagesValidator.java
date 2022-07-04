package org.example.BookSpring.bookStorage.Validator;

import org.example.BookSpring.bookStorage.ItemOp;

public class PagesValidator<T> extends Validator<T> {

    protected PagesValidator(Validator<T> nextValidation) {
        super(nextValidation);
    }

    @Override
    public void processValidation(T request) {
        if ( (Integer)request < 1 )
            throw new IllegalArgumentException(" Wrong Pages !");


        super.processValidation(request);
    }
}
