package com.flee1son_dev.user.exceptions;

public class NegativeMoneyAmountException extends GlobalBankingException{
    public NegativeMoneyAmountException(){
        super("Account's money amount cannot be negative");
    }
}
