package org.example.BookSpring.bookStorage.Validator;

public class NumberValidator<T> extends Validator<T>{

    public NumberValidator(Validator<T> nextValidation) {
        super(nextValidation);
    }

    public void processValidation(T request) {
        if ( (Integer)request < 1 )
            throw new IllegalArgumentException(" Wrong number !");


        super.processValidation(request);
    }
}
