package com.flee1son_dev.console.commands;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.flee1son_dev.console.ConsoleOperationType;
import com.flee1son_dev.user.AccountService;

@Component 
public class AccountDepositCommand implements OperationCommand{
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountDepositCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override 
    public void execute(){
        System.out.print("Enter the account's ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter the deposit amount: ");
        Double depositAmount = scanner.nextDouble();
        scanner.nextLine();

        if(id <= 0 || id == null){
            System.out.println("Id cannot must be a null or empty");
            return;
        }
        if(depositAmount <= 0 || depositAmount == null){
            System.out.println("Deposit amount cannot be negative or empty");
            return;
        }
        accountService.depositAccount(id, depositAmount);
        System.out.println("Account with ID " + id + " has been deposited amount " + depositAmount);
    }

    @Override 
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.ACCOUNT_DEPOSIT;
    }
}
