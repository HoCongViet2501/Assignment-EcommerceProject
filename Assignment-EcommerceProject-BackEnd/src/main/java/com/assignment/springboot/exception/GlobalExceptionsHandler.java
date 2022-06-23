package com.assignment.springboot.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionsHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception,WebRequest request){
        ErrorResponse errorResponse=new ErrorResponse(new Date(),exception.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> globalExceptionHandling(Exception exception,WebRequest request){
        ErrorResponse errorResponse=new ErrorResponse(new Date(),exception.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
