package org.example.BookSpring.bookStorage.validators;

public class ChainValidator {

    Validator chainValidator;

    public ChainValidator(){
        Validator();
    }

    private void Validator(){
        chainValidator = new NotNull(new NotBlank(new LengthValidator(new PagesValidator(new DateValidator(null)))));
    }
    public <T> T processValidator(T request) {
        chainValidator.processValidation(request);
        return request;
    }
}
