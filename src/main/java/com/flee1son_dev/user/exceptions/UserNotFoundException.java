package com.flee1son_dev.user.exceptions;

public class UserNotFoundException extends GlobalBankingException{
    private Long userId;

    public UserNotFoundException(Long userId){
        super("Not found user by id: " + userId);
        this.userId  = userId;
    }

    public Long getUserId(){
        return userId;
    }
}
