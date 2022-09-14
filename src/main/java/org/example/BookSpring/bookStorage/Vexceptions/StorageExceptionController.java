package org.example.BookSpring.bookStorage.Vexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class StorageExceptionController extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<Object> itemNotFoundHandler(ItemNotFoundException exception) {
        return errorResponse(exception, HttpStatus.NOT_FOUND);
    }


    @ResponseBody
    @ExceptionHandler(ItemBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> itemBadRequestHandler(ItemBadRequestException exception) {
        return errorResponse(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> errorResponse(Exception e, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(e.getMessage());
    }
}


