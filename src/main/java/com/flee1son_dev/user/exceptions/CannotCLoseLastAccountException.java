package com.flee1son_dev.user.exceptions;

public class CannotCLoseLastAccountException extends GlobalBankingException{
    public CannotCLoseLastAccountException(){
        super("Cannot close last user's account");
    }
}
