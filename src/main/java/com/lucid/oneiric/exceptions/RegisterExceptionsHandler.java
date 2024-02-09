package com.lucid.oneiric.exceptions;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RegisterExceptionsHandler {

    @ExceptionHandler(MissingLoginDataException.class)
    public ResponseEntity<String> handleMissingLoginDataException(MissingLoginDataException missingLoginException) {
        missingLoginException.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(missingLoginException.getMessage());

    }
    @ExceptionHandler(MissingPasswordDataException.class)
    public ResponseEntity<String> handleMissingPasswordDataException(MissingPasswordDataException missingPasswordDataException) {
        missingPasswordDataException.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(missingPasswordDataException.getMessage());
    }
    @ExceptionHandler(MissingEmailDataException.class)
    public ResponseEntity<String> handleMissingEmailDataException(MissingEmailDataException missingEmailDataException) {
        missingEmailDataException.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(missingEmailDataException.getMessage());

    }

    @ExceptionHandler(MissingMultipleRequiredFieldsException.class)
    public ResponseEntity<String> handleMissingMultipleRequiredFieldsException(MissingMultipleRequiredFieldsException missingMultipleRequiredFieldsException) {
        missingMultipleRequiredFieldsException.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(missingMultipleRequiredFieldsException.getMessage());
    }
}
