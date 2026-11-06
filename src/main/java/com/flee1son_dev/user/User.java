package com.flee1son_dev.user;

import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;


public class User {
    @Nonnull 
    private Long id;
    @Nonnull 
    private String login;
    @Nullable 
    private List<Account> accounts;

    public User(Long id, String login, List<Account> accounts){
        this.id = id;
        this.login = login;
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


}
