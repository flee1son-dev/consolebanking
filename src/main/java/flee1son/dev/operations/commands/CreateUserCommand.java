package flee1son.dev.operations.commands;

import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import flee1son.dev.user.UserService;
import flee1son.dev.utils.ConsoleInputService;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CreateUserCommand implements OperationCommand {
    private final UserService userService;
    private final ConsoleInputService consoleInputService;

    public CreateUserCommand(UserService userService, ConsoleInputService consoleInputService) {
        this.userService = userService;
        this.consoleInputService = consoleInputService;
    }

    @Override
    public void execute(){
        String login = consoleInputService.getStringFromUser("Enter the user's login: ");
        var user = userService.userCreate(login);
    }

    @Override
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.USER_CREATE;
    }
}
