package flee1son.dev.operations.commands;

import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import flee1son.dev.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CreateUserCommand implements OperationCommand {
    private final UserService userService;
    private final Scanner scanner;

    public CreateUserCommand(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute(){
        System.out.print("Enter the user's login: ");
        String login = scanner.nextLine().trim();


        var user = userService.userCreate(login);
        System.out.println("User succesfully created: " + user.toString() + "\n");
    }

    @Override
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.USER_CREATE;
    }
}
