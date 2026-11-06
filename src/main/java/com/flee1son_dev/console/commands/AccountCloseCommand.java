package com.flee1son_dev.console.commands;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.flee1son_dev.console.ConsoleOperationType;
import com.flee1son_dev.user.AccountService;

@Component 
public class AccountCloseCommand implements OperationCommand{
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountCloseCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override 
    public void execute(){
        System.out.print("Enter closing account's id: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        if (id <= 0 || id == null){
            System.out.println("Id cannot must be a null or empty");
            return;
        }
        accountService.closeAccount(id);
        System.out.println("Account with ID " + id + "has been closed \n");
    }

    @Override 
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}
