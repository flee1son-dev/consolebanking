package flee1son.dev.account;

import flee1son.dev.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AccountService {
    private final Map<Integer, Account> accountMap = new HashMap<Integer, Account>();
    private final UserService userService;
    private final AtomicInteger idGen = new AtomicInteger();

    public AccountService(UserService userService) {
        this.userService = userService;
    }

    private int nextId(){
        return idGen.incrementAndGet();
    }

    public Account createAccount(int userId){
        return null;
    }
}
