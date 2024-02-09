package com.lucid.oneiric.exceptions;

public class MissingPasswordDataException extends RuntimeException {
    public MissingPasswordDataException() {
        super("User must provide a password.");

    }
}
