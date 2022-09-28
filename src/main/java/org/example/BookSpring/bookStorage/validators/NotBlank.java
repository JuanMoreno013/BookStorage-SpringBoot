package org.example.BookSpring.bookStorage.validators;

public class NotBlank extends Validator{

    protected NotBlank(Validator nextValidation) {
        super(nextValidation);
    }

    @Override
    public void processValidation(Object request) {
        if(request instanceof String){
            if ((request.toString()).isBlank())
                throw new IllegalArgumentException(" Something is BLANK ! ");
        }

        super.processValidation(request);

    }
}
