package org.example.BookSpring.bookStorage.Validator;


public abstract class Validator<T> implements ValidatorI<T>{

    private final Validator<T> nextValidation;

    public Validator(Validator<T> nextValidation) {
        this.nextValidation = nextValidation;
    }

    public void processValidation(T request){
        if(nextValidation != null)
            nextValidation.processValidation(request);
    }
}
