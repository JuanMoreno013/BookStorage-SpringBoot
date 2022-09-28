package org.example.BookSpring.bookStorage.validators;


public class PagesValidator extends Validator {

    protected PagesValidator(Validator nextValidation) {
        super(nextValidation);
    }

    @Override
    public void processValidation(Object request) {
        if(request instanceof Integer){
            if ( (Integer)request < 1 )
                throw new IllegalArgumentException(" Wrong Number !");
        }


        super.processValidation(request);
    }
}
