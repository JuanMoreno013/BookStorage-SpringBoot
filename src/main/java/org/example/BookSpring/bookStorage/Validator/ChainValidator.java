package org.example.BookSpring.bookStorage.Validator;


import java.time.LocalDate;

public class ChainValidator {

    Validator<String> chainString;
    Validator<Integer> chainInteger;
    Validator<LocalDate> chainDate;
    Validator<Integer> chainLetter;


    public ChainValidator(){
        stringValidator();
        intValidator();
        dateValidator();
        letterValidator();
    }

    private void stringValidator(){
        chainString = new NotNull<>(new NotBlank<>(new LengthValidator<>(null)));
    }
    public String processValidator(String request){
        chainString.processValidation(request);
        return request;
    }

    private void intValidator(){
        chainInteger = new NotNull<>( new PagesValidator<>(null));
    }
    public int processValidator(int request){
        chainInteger.processValidation(request);
        return request;
    }

    private void letterValidator(){
        chainLetter = new NotNull<>( new NumberValidator<>(null));
    }
    public int processValidator2(int request){
        chainLetter.processValidation(request);
        return request;
    }

    private void dateValidator(){
        chainDate = new NotNull<>(new DateValidator<>(null));
    }

    public LocalDate processValidator(LocalDate request){
        chainDate.processValidation(request);
        return request;
    }



}
