package com.lucid.oneiric.errors.register;

public class MissingLoginData {
    private int status;
    private String error, message;

    public MissingLoginData(int status) {
        this.status = status;
        this.error = "Missing field data.";
        this.message = "Login field must not be empty.";
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
