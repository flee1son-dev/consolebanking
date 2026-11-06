package com.flee1son_dev.user.exceptions;

public abstract class GlobalBankingException extends RuntimeException {
    protected GlobalBankingException(String message) {
        super(message);
    }
}