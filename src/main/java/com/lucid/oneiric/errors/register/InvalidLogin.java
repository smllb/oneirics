package com.lucid.oneiric.errors.register;

public class InvalidLogin {
    private int status;
    private String error, message;

    public InvalidLogin(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
