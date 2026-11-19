package flee1son.dev.user.exceptions;

public class LoginByExistsException extends UserExceptions{
    public LoginByExistsException() {
        super("User already exists");
    }
}
