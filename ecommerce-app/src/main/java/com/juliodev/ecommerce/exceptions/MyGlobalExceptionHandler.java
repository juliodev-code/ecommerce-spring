package com.juliodev.ecommerce.exceptions;

import com.juliodev.ecommerce.payload.APIResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> responseMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach( error -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            responseMap.put(fieldName, message);
        });
        return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> myResourceNotFoundException(ResourceNotFoundException ex){
        APIResponse response = new APIResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(false);
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(APIException.class)
    public ResponseEntity<APIResponse> myAPIException(APIException ex){
        APIResponse response = new APIResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(false);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
