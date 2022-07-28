package org.example.BookSpring.bookStorage.Validator;

import java.time.LocalDate;

public class DateValidator extends Validator{

    public DateValidator(Validator nextValidation) {
        super(nextValidation);
    }

    @Override
    public void processValidation(Object request) {
        if(request instanceof LocalDate)
        {
            if ( ((LocalDate) request).isAfter(LocalDate.now()))
                throw new IllegalArgumentException(" Wrong Date! ");
        }

        super.processValidation(request);
    }
}
