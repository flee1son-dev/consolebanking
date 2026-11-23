package sorokin.java.course.user;

import org.springframework.stereotype.Component;
import sorokin.java.course.account.AccountService;

import java.util.*;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    public UserService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public User createUser(String login) {
        String normalizedLogin = validateLogin(login);
        var user = new User(normalizedLogin, new ArrayList<>());
        userRepository.saveUser(user);

        var defaultAccount = accountService.createAccount(user);
        user.getAccountList().add(defaultAccount);

        return user;
    }

    public User findUserById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("user id must be > 0");
        }
        var user = userRepository.findById(id);
        if (user == null) {
            throw new IllegalArgumentException("No such user with id=%s".formatted(id));
        }
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    private String validateLogin(String login) {
        if (login == null || login.isBlank()) {
            throw new IllegalArgumentException("login must not be blank");
        }
        return login.trim();
    }
}
