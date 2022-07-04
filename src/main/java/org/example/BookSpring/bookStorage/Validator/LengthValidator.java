package org.example.BookSpring.bookStorage.Validator;

public class LengthValidator<T extends String> extends Validator<T>{

    public LengthValidator(Validator<T> nextValidation) {
        super(nextValidation);
    }

    @Override
    public void processValidation(T request) {
        if (request.length() > 20 || request.length() < 3)
            throw new IllegalArgumentException("Wrong length! ");

        super.processValidation(request);
    }
}
