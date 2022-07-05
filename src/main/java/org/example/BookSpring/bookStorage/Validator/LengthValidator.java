package org.example.BookSpring.bookStorage.Validator;

public class LengthValidator extends Validator{

    public LengthValidator(Validator nextValidation) {
        super(nextValidation);
    }

    @Override
    public void processValidation(Object request) {
        if (request instanceof String)
        {
            if (((String)request).length() > 20 || ((String)request).length() < 3)
                throw new IllegalArgumentException("Wrong length! ");
        }


        super.processValidation(request);
    }
}
