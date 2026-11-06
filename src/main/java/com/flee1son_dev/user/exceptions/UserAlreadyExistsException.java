package com.flee1son_dev.user.exceptions;


public class UserAlreadyExistsException extends GlobalBankingException{
    public  UserAlreadyExistsException(){
        super("User already exists");
    }
}
