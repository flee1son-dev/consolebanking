package flee1son.dev.operations.commands;

import flee1son.dev.account.Account;
import flee1son.dev.account.AccountService;
import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import flee1son.dev.user.UserService;
import flee1son.dev.utils.ConsoleInputService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountCreateCommand implements OperationCommand {
    private final AccountService accountService;
    private final ConsoleInputService consoleInputService;

    public AccountCreateCommand(AccountService accountService,ConsoleInputService consoleInputService) {
        this.accountService = accountService;
        this.consoleInputService = consoleInputService;
    }

    @Override
    public void execute() {
        int id = consoleInputService.getIntFromUser("Enter the user's ID ");
        Account account = accountService.createAccount(id);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}
