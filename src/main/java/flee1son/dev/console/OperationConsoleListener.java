package flee1son.dev.console;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import flee1son.dev.utils.ShutdownManager;
import org.springframework.stereotype.Component;



@Component
public class OperationConsoleListener implements Runnable{
    private final Map<ConsoleOperationType, OperationCommand> commandMap;
    private final Scanner scanner;
    private final ShutdownManager shutdownManager;

    public OperationConsoleListener(
            List<OperationCommand> commands,
            Scanner scanner,
            ShutdownManager shutdownManager
    ) {
        this.scanner = scanner;
        this.shutdownManager = shutdownManager;
        this.commandMap = new ConcurrentHashMap<>();
        commands.forEach(cmd -> commandMap.put(cmd.getOperationType(), cmd));
    }

    @Override
    public void run(){
        while (shutdownManager.isRunning()) {
            System.out.println("Please enter one of operation type: ");
            for(ConsoleOperationType type : ConsoleOperationType.values()){
                System.out.println(type);
            }
            try{
                String input = scanner.nextLine().trim();
                if(input.isEmpty()){
                    continue;
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
        shutdownManager.shutdown();
    }
}

