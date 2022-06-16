package com.example.hamromistiri.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity <ApiResponse>resourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        ApiResponse apiResponse = new ApiResponse(new Date(),ex.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <Map<String,String>> handleMethodNotValidException(MethodArgumentNotValidException ex){
        Map <String ,String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String filedName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            resp.put(filedName,message);
        });
        return new ResponseEntity<Map<String,String>>(resp, HttpStatus.BAD_REQUEST);
    }
}
