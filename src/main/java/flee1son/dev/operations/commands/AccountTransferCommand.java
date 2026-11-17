package flee1son.dev.operations.commands;

import flee1son.dev.account.Account;
import flee1son.dev.account.AccountService;
import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountTransferCommand implements OperationCommand {
    private final AccountService accountService;
    private final Scanner scanner;

    @Override
    public void execute() {
        System.out.print("Enter source account: ");
        int senderId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter target account: ");
        int recipientId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter transfer amount: ");
        double transferAmount = scanner.nextDouble();
        scanner.nextLine();

        Account account = accountService.transferAccount(senderId, recipientId, transferAmount);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }

    public AccountTransferCommand(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }
}
