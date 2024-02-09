package com.lucid.oneiric.enums;

public enum AccountType {
    USER(0),
    ADMIN(1);

    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

