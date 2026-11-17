package flee1son.dev.account.exceptions;

public class AccountTransferException extends AccountExceptions{
    public AccountTransferException() {
        super("TRANSFER ERROR: Account's IDs conflicted");
    }
}
