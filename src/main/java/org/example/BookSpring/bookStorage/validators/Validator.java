package org.example.BookSpring.bookStorage.validators;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Validator implements ValidatorI{
    private final Validator nextValidation;

    public void processValidation(Object request){
        if(nextValidation != null)
            nextValidation.processValidation(request);
    }
}
