package flee1son.dev.operations.commands;

import flee1son.dev.account.Account;
import flee1son.dev.account.AccountService;
import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountDepositCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountDepositCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter the account's ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the deposit: ");
        double deposit = scanner.nextDouble();
        scanner.nextLine();

        accountService.depositAccount(id, deposit);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSIT;
    }
}
