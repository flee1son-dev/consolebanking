package flee1son.dev.operations.commands;

import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import flee1son.dev.user.User;
import flee1son.dev.user.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllUsersCommand implements OperationCommand {
    private final UserService userService;

    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(){
        List<User> listUsers = userService.getAllUsers();
        System.out.println("List of all users: " + listUsers);
    }

    @Override
    public ConsoleOperationType getOperationType(){
        return ConsoleOperationType.SHOW_ALL_USERS;
    }



}
