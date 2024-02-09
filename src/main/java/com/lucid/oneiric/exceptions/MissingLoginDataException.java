package com.lucid.oneiric.exceptions;

public class MissingLoginDataException extends RuntimeException {
    public MissingLoginDataException()  {
        super("Login field must not be empty.");
    }
}
