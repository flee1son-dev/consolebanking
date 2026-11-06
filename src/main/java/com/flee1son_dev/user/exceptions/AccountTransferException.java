package com.flee1son_dev.user.exceptions;

public class AccountTransferException extends GlobalBankingException{
    

    public AccountTransferException(){
        super("Accounts conflicted");
    }

}
