package com.lucid.oneiric.exceptions;

public class MissingMultipleRequiredFieldsException extends RuntimeException{
    public MissingMultipleRequiredFieldsException() {
        super("One or more fields not provided properly.");
    }
}
