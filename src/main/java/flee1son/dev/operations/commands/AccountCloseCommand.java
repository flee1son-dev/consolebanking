package flee1son.dev.operations.commands;

import flee1son.dev.account.AccountService;
import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountCloseCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountCloseCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter account's ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        accountService.closeAccount(id);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE;
    }
}

