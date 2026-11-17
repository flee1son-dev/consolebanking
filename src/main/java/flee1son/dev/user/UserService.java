package flee1son.dev.user;

import flee1son.dev.account.Account;
import flee1son.dev.account.AccountService;
import flee1son.dev.user.exceptions.LoginByExistsException;
import flee1son.dev.user.exceptions.UserExceptions;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {
    private final AtomicInteger idGen = new AtomicInteger();
    private final UserRepository userRepository;
    private final AccountService accountService;

    private int nextId(){
        return idGen.incrementAndGet();
    }

    public UserService(
            UserRepository userRepository,
            AccountService accountService
            ) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public User userCreate(String login){
        if(login.isBlank() || login.isEmpty()){
            throw new UserExceptions("Login cannot be a blank or empty");
        }

        int id = nextId();
        User user = new User(
                id,
                login,
                new ArrayList<>()
        );
        if (userRepository.existsByLogin(user)){
            throw new LoginByExistsException();
        }
        userRepository.save(user);
        Account account = accountService.createAccount(id);

        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAllUsers();
    }


}
