package flee1son.dev.account.exceptions;

public class AccountNotFoundException extends AccountExceptions{
    public AccountNotFoundException(int id) {
        super("Account by id: " + id + " not found!");
    }
}
