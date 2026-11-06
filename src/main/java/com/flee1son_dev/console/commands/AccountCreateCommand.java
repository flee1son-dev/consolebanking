package com.flee1son_dev.console.commands;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.flee1son_dev.console.ConsoleOperationType;
import com.flee1son_dev.user.AccountService;

@Component 
public class AccountCreateCommand implements OperationCommand{
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountCreateCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override 
    public void execute(){
        System.out.print("Enter the user's id: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        if(id <= 0 || id == null){
            System.out.println("Id cannot must be a null or empty");
            return; 
        }
        var account = accountService.createAccount(id);
        System.out.println("Account created: " + account);
    }

    @Override 
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.ACCOUNT_CREATE;
    }

    

}
