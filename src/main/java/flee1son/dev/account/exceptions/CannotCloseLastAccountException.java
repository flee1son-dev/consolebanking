package flee1son.dev.account.exceptions;

public class CannotCloseLastAccountException extends AccountExceptions{
    public CannotCloseLastAccountException() {
        super("Cannot close last account!");
    }
}
