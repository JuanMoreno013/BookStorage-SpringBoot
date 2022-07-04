package org.example.BookSpring.bookStorage.Validator;

import org.example.BookSpring.bookStorage.ItemOp;

public class NotNull<T> extends Validator<T>{

    public NotNull(Validator<T> nextValidator){
        super(nextValidator);
    }

    @Override
    public void processValidation(T request) {
        if (request== null)
            throw new IllegalArgumentException(" It is Null");

        super.processValidation(request);
    }
}
