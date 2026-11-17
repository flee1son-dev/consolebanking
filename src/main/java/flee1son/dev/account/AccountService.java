package flee1son.dev.account;

import flee1son.dev.account.exceptions.AccountNotFoundException;
import flee1son.dev.account.exceptions.AccountTransferException;
import flee1son.dev.account.exceptions.CannotCloseLastAccountException;
import flee1son.dev.account.exceptions.NegateMoneyException;
import flee1son.dev.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AtomicInteger idGen = new AtomicInteger();
    private final AccountProperties accountProperties;

    public AccountService(UserRepository userRepository,
                          AccountRepository accountRepository,
                          AccountProperties accountProperties
    ) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountProperties = accountProperties;
    }

    private int nextId(){
        return idGen.incrementAndGet();
    }

    public Account createAccount(int userId){
        Account account = new Account(
                nextId(),
                userId,
                accountProperties.getDefaultMoneyAmount()
        );
        var accounts = userRepository.findUserById(userId)
                .orElseThrow()
                .getAccountList();
        accounts.add(account);

        accountRepository.save(account);

        return account;
    }

    public Account depositAccount(int accountId, double deposit){
        if(deposit < 0) {
            throw new NegateMoneyException("Deposit cannot be a negate");
        }
        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        var userAccounts = userRepository.findUserById(account.getUserId())
                .orElseThrow()
                .getAccountList();

        int idx = userAccounts.indexOf(account);

        double newMoneyAmount = account.getMoneyAmount() + deposit;
        account.setMoneyAmount(newMoneyAmount);

        userAccounts.set(idx, account);

        return account;
    }

    public Account withdrawAccount(int accountId, double withdraw){
        if(withdraw < 0){
            throw new NegateMoneyException("Withdraw cannot be a negate");
        }
        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        var userAccounts = userRepository.findUserById(account.getUserId())
                .orElseThrow()
                .getAccountList();
        int idx = userAccounts.indexOf(account);

        double newMoneyAmount = account.getMoneyAmount() - withdraw;
        if(newMoneyAmount < 0){
            throw new NegateMoneyException("Money amount cannot be a negate");
        }

        account.setMoneyAmount(newMoneyAmount);
        userAccounts.set(idx, account);

        return account;
    }

    public Account transferAccount(
            int senderAccountId,
            int recipientAccountId,
            double transferAmount
            ){
        if (transferAmount <= 0){
            throw new NegateMoneyException("Transfer amount cannot be negate or empty");
        }
        if(senderAccountId == recipientAccountId){
            throw new AccountTransferException();
        }

        var senderAccount = accountRepository.findById(senderAccountId)
                .orElseThrow(() -> new AccountNotFoundException(senderAccountId));
        var recipientAccount = accountRepository.findById(recipientAccountId)
                .orElseThrow(() -> new AccountNotFoundException(recipientAccountId));


        var senderUserAccounts = userRepository.findUserById(senderAccount.getUserId())
                .orElseThrow()
                .getAccountList();
        int senderIdx = senderUserAccounts.indexOf(senderAccount);

        var recipientUserAccounts = userRepository.findUserById(recipientAccount.getUserId())
                .orElseThrow()
                .getAccountList();
        int recipientIdx = recipientUserAccounts.indexOf(recipientAccount);


        boolean sameOwner = senderAccount.getUserId() == recipientAccount.getUserId();
        double comission = sameOwner ?
                0 : transferAmount * accountProperties.getDefaultTransferComission();

        double newSenderAmount = senderAccount.getMoneyAmount() - transferAmount;
        if(newSenderAmount < 0){
            throw new NegateMoneyException("Sender money amount cannot be a negate");
        }

        double newRecipientAmount = recipientAccount.getMoneyAmount() + transferAmount - comission;

        senderAccount.setMoneyAmount(newSenderAmount);
        recipientAccount.setMoneyAmount(newRecipientAmount);

        senderUserAccounts.set(senderIdx, senderAccount);
        recipientUserAccounts.set(recipientIdx, recipientAccount);

        return recipientAccount;
    }

    public void closeAccount(int accountId){
        var account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        var userAccounts = userRepository.findUserById(account.getUserId())
                .orElseThrow()
                .getAccountList();
        if(userAccounts.size() <= 1){
            throw new CannotCloseLastAccountException();
        }
        if(account.getMoneyAmount() > 0) {
            var otherUserAccount = userAccounts.stream()
                    .filter(a -> a.getId() != accountId)
                    .min(Comparator.comparing(Account::getId))
                    .orElseThrow();
            otherUserAccount.setMoneyAmount(account.getMoneyAmount() + otherUserAccount.getMoneyAmount());
        }
        userAccounts.removeIf(a -> a.getId() == accountId);
        accountRepository.close(accountId);


    }
}
