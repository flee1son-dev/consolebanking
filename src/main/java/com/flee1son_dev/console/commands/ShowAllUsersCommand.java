package com.flee1son_dev.console.commands;


import org.springframework.stereotype.Component;

import com.flee1son_dev.console.ConsoleOperationType;
import com.flee1son_dev.user.UserService;

@Component 
public class ShowAllUsersCommand implements OperationCommand{
    private final UserService userService;

    public ShowAllUsersCommand(
        UserService userService
    ){
        this.userService = userService;
    }

    @Override 
    public void execute(){
        System.out.println("List of all users: " + userService.getAllUsers());
    }

    @Override 
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.SHOW_ALL_USERS;
    }

}
