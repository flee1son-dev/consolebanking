package flee1son.dev.operations.commands;

import flee1son.dev.account.Account;
import flee1son.dev.account.AccountService;
import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import flee1son.dev.user.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountCreateCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountCreateCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter the user's ID ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Account account = accountService.createAccount(id);

        System.out.println("Account succesfully created: " + account.toString());
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE;
    }
}
