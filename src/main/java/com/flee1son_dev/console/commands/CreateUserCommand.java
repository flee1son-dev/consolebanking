package com.flee1son_dev.console.commands;

import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.flee1son_dev.console.ConsoleOperationType;
import com.flee1son_dev.user.UserService;

@Component 
public class CreateUserCommand implements OperationCommand{
    private final UserService userService;
    private final Scanner scanner;
    
    public CreateUserCommand(UserService userService, Scanner scanner){
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override 
    public void execute(){
        System.out.print("Enter the user's login: ");
        String login = scanner.nextLine().trim();
        scanner.nextLine();
        if(login.isEmpty() || login.isBlank()) {
            System.out.println("Login cannot must be empty or blank");
            return;
        }
        var user = userService.createUser(login);
        System.out.println("User created: " + user);
    }

    @Override 
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.USER_CREATE;
    }

}
