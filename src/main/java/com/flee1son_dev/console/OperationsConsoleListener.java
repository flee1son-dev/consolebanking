package com.flee1son_dev.console;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.flee1son_dev.console.commands.OperationCommand;

@Component
public class OperationsConsoleListener implements Runnable{
    private final Map<ConsoleOperationType, OperationCommand> commandMap;
    private final Scanner scanner;
    private volatile boolean running = true;

    public OperationsConsoleListener(List<OperationCommand> commands, Scanner scanner) {
        this.scanner = scanner;
        this.commandMap = new ConcurrentHashMap<>();
        commands.forEach(cmd -> commandMap.put(cmd.getOperationType(), cmd));
    }

    @Override 
    public void run(){
        while (running) {
            System.out.println("Please enter one of operation type: ");
            for(ConsoleOperationType type : ConsoleOperationType.values()){
                System.out.println(type);
            }
            try{
                String input = scanner.nextLine().trim();
                if(input.isEmpty()){
                    continue;
                }
                if("EXIT".equalsIgnoreCase(input)){
                    running = false;
                    System.out.println("Shutting down...");
                    break;
                }
                ConsoleOperationType opType = ConsoleOperationType.valueOf(input.toUpperCase());

                OperationCommand command = commandMap.get(opType);

                if(command != null){
                    command.execute();
                }else{
                    System.out.println("Command not implemented: " + opType);
                }

            }catch(IllegalArgumentException e){
                System.out.println("Unknown operation");
            }catch(Exception e){
                System.out.println("Error in runtime: " + e.getMessage());
            }
        }
    }

    public void stop(){
        running = false;
    }
}
