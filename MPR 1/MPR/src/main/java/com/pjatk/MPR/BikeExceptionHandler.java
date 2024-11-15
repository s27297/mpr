package com.pjatk.MPR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class BikeExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(BikeNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest re)
    {
        return  ResponseEntity.notFound().build();
    }

    protected ResponseEntity<Object> handleAlreadyExist(RuntimeException ex, WebRequest re)
    {
        return  ResponseEntity.badRequest().body("Already exists");
    }

}
