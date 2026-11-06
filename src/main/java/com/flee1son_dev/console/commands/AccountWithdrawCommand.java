package com.flee1son_dev.console.commands;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.flee1son_dev.console.ConsoleOperationType;
import com.flee1son_dev.user.AccountService;
@Component 
public class AccountWithdrawCommand implements OperationCommand{
    private final AccountService accountService;
    private final Scanner scanner;
    public AccountWithdrawCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override 
    public void execute(){
        System.out.print("Enter the account's ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter the withdraw amount: ");
        Double amount = scanner.nextDouble();

        if(id <= 0 || id == null){
            System.out.println("ID cannot be negative or null");
            return;
        }
        if(amount <= 0 || amount == null){
            System.out.println("Amount cannot be negative or empty");
            return;
        }

        accountService.withdrawAccount(id, amount);
        System.out.println("Amount " + amount + " from account ID " + id + "has been withdrawed \n");
    }

    @Override
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.ACCOUNT_WITHDRAW;
    }

    
}
