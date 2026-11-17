package flee1son.dev.account;

import flee1son.dev.account.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccountRepository {
    private final Map<Integer, Account> accountMap = new HashMap<>();


    public void save(Account a){
        accountMap.put(a.getId(), a);
    }

    public Optional<Account> findById(int id){
        return Optional.ofNullable(accountMap.get(id));


    }


    public void close(int accountId) {
        accountMap.remove(accountId);

    }
}
