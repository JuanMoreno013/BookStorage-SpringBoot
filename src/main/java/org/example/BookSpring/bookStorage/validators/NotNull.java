package org.example.BookSpring.bookStorage.validators;

public class NotNull extends Validator{

    public NotNull(Validator nextValidator){
        super(nextValidator);
    }

    @Override
    public void processValidation(Object request) {
        if (request== null)
            throw new IllegalArgumentException(" Something is NULL !");

        super.processValidation(request);
    }
}
