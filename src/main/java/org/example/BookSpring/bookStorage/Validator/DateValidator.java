package org.example.BookSpring.bookStorage.Validator;

import java.time.LocalDate;

public class DateValidator<T extends LocalDate> extends Validator<T>{

    public DateValidator(Validator<T> nextValidation) {
        super(nextValidation);
    }

    @Override
    public void processValidation(T request) {
        if (request.isAfter(LocalDate.now()))
            throw new IllegalArgumentException(" Wrong Date! ");

        super.processValidation(request);
    }
}
