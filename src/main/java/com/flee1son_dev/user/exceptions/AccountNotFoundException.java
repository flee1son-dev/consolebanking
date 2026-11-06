package com.flee1son_dev.user.exceptions;

public class AccountNotFoundException extends GlobalBankingException {
    private Long accountId;

    
    public AccountNotFoundException(Long accountId){
        super("Account not found by id: " + accountId);
        this.accountId = accountId;
    }
    
    
    public Long getAccoutId() {
        return accountId;
    }

}
