package com.flee1son_dev.user;

import java.util.Objects;


import jakarta.annotation.Nonnull;

/**
 * Account
 */
public class Account {
    @Nonnull 
    private  Long id;
    @Nonnull
    private Long userId;
    @Nonnull 
    private double moneyAmount;

    public Account(
        Long id,
        Long userId,
        double moneyAmount 
    ){
        
        this.id = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        Account account = (Account) o;
        return id == account.getId() &&
                userId == account.getUserId();
    }

    @Override 
    public int hashCode() {
        return Objects.hash(id, userId);
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }


    public double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", userId=" + userId + ", moneyAmount=" + moneyAmount + "]";
    }

}
