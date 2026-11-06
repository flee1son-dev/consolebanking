package com.flee1son_dev.console.commands;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.flee1son_dev.console.ConsoleOperationType;
import com.flee1son_dev.user.AccountService;

@Component 
public class AccountTransferCommand implements OperationCommand{
    private final AccountService accountService;
    private final Scanner scanner;
    public AccountTransferCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override 
    public void execute(){
        System.out.print("Enter source account's ID: ");
        Long senderAccountId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter targer account's ID: ");
        Long recipiesAccountId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Enter amount to transfer: ");
        Double amount = scanner.nextDouble();
        scanner.nextLine();

        if(
            senderAccountId <= 0 || senderAccountId == null ||
            recipiesAccountId <= 0 || recipiesAccountId == null
        ){
            System.out.println("Id cannot must be a null or empty");
            return; 
        }
        if(amount <= 0 || amount == null){
            System.out.println("Amount cannot be a negative");
            return;
        }
        accountService.transferAccount(senderAccountId, senderAccountId, amount);
        System.out.println(
            "Amount " + amount + "transferred from account's ID " + 
            senderAccountId + "from to account's ID " + recipiesAccountId
        );
    }

    @Override 
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }

}
