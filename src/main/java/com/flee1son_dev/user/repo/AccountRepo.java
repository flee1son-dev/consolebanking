package com.flee1son_dev.user.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.flee1son_dev.user.Account;

public class AccountRepo {
    private final Map<Long, Account> accountMap = new HashMap<>();


    public void save(Account a){
        accountMap.put(a.getId(), a);
    }
    
    public Optional<Account> findById(Long accountId){
        return Optional.ofNullable(accountMap.get(accountId));
    }

    public void close(Long accountId){
        accountMap.remove(accountId);
    }
}
