package flee1son.dev.operations.commands;

import flee1son.dev.account.Account;
import flee1son.dev.account.AccountService;
import flee1son.dev.operations.ConsoleOperationType;
import flee1son.dev.operations.OperationCommand;
import flee1son.dev.utils.ConsoleInputService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountTransferCommand implements OperationCommand {
    private final AccountService accountService;
    private final ConsoleInputService consoleInputService;

    public AccountTransferCommand(AccountService accountService, ConsoleInputService consoleInputService) {
        this.accountService = accountService;
        this.consoleInputService = consoleInputService;
    }

    @Override
    public void execute() {
        int senderId = consoleInputService.getIntFromUser("Enter source account: ");
        int recipientId = consoleInputService.getIntFromUser("Enter target account: ");
        double transferAmount = consoleInputService.getDoubleFromUser("Enter transfer amount: ");
        Account account = accountService.transferAccount(senderId, recipientId, transferAmount);
    }

    @Override
    public ConsoleOperationType getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER;
    }


}
