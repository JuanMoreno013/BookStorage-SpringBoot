package org.example.BookSpring.bookStorage.Validator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Validator implements ValidatorI{
    private final Validator nextValidation;

    public void processValidation(Object request){
        if(nextValidation != null)
            nextValidation.processValidation(request);
    }
}
