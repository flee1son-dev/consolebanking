package flee1son.dev.user.exceptions;

public class UserNotFound extends UserExceptions {
    public UserNotFound(int id) {
        super("User by id: " + id + " not found");
    }
}
