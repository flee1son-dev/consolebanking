package com.flee1son_dev.user;

import java.util.List;


public class User {
    private Long id;

    private String login;

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
