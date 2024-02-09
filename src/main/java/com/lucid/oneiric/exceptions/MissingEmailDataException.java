package com.lucid.oneiric.exceptions;

public class MissingEmailDataException extends RuntimeException{
    public MissingEmailDataException() {
        super("User must provide an email.");
    }
}
