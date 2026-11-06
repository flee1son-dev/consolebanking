package com.flee1son_dev.user;

import java.util.concurrent.atomic.AtomicLong;


import org.springframework.stereotype.Service;

import com.flee1son_dev.user.exceptions.AccountNotFoundException;
import com.flee1son_dev.user.exceptions.AccountTransferException;
import com.flee1son_dev.user.exceptions.CannotCLoseLastAccountException;
import com.flee1son_dev.user.exceptions.NegativeMoneyAmountException;
import com.flee1son_dev.user.repo.AccountRepo;
import com.flee1son_dev.user.repo.UserRepo;


@Service 
public class AccountService {
    private final UserRepo userRepo;
    private final UserService userService;
    private final AccountRepo accountRepo;
    private final AccountProperties accountProperties;
    private final AtomicLong accountIdCounter = new AtomicLong();

    private Long nextId(){
        return accountIdCounter.incrementAndGet();
    }

    public AccountService(
        UserRepo userRepo,
        AccountRepo accountRepo,
        UserService userService,
        AccountProperties accountProperties
    ){
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
        this.userService = userService;
        this.accountProperties = accountProperties;
    }


    public Account createAccount(Long id){
        var user = userService.getUserById(id);

        var account = new Account(
            nextId(), 
            user.getId(),
            accountProperties.getDefaultAmount()
        );

        accountRepo.save(account);
        user.getAccounts().add(account);
        userRepo.save(user);
        
        return account;
    }

    public void closeAccount(Long accountId) {
        var account = accountRepo.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        
        var user = userRepo.findById(account.getUserId()).orElseThrow();

        var userAccounts = user.getAccounts();

        if(userAccounts.size() <= 1){
            throw new CannotCLoseLastAccountException();
        }
        if(account.getMoneyAmount() > 0){
            var otherUserAccount = userAccounts.stream()
                    .filter(a -> !a.getId().equals(accountId))
                    .findFirst()
                    .orElseThrow();
            otherUserAccount.setMoneyAmount(otherUserAccount.getMoneyAmount() + account.getMoneyAmount());
            accountRepo.save(otherUserAccount);
        }
        userAccounts.removeIf(a -> a.getId().equals(accountId));
        accountRepo.close(accountId);
        userRepo.save(user);
    }


    public Account depositAccount(Long accountId, double deposit){
        if(deposit < 0){
            throw new NegativeMoneyAmountException();
        }
        var account = accountRepo.findById(accountId).orElseThrow(
            () -> new AccountNotFoundException(accountId)
        );
        var user = userRepo.findById(account.getUserId()).orElseThrow();
        var userAccounts = user.getAccounts();
        int idx = userAccounts.indexOf(account);
        
        var newAmount = account.getMoneyAmount() + deposit;
        account.setMoneyAmount(newAmount);
        
        userAccounts.set(idx, account);
        

        accountRepo.save(account);
        userRepo.save(user);

        return account;
    }


    public void transferAccount(
        Long senderAccountId,
        Long recipientAccountId,
        double transferAmount
    ){
        if(transferAmount <= 0){
            throw new NegativeMoneyAmountException();
        }
        if(senderAccountId.equals(recipientAccountId)){
            throw new AccountTransferException();
        }
        var senderAccount = accountRepo.findById(senderAccountId).orElseThrow(
            () -> new AccountNotFoundException(senderAccountId)
        );
        var recipientAccount = accountRepo.findById(recipientAccountId).orElseThrow(
            () -> new AccountNotFoundException(recipientAccountId)
        );

        var senderUser = userRepo.findById(senderAccount.getUserId()).orElseThrow();
        var senderUserAccounts = senderUser.getAccounts();

        var recipientUser = userRepo.findById(recipientAccount.getUserId()).orElseThrow();
        var recipientUserAccounts = recipientUser.getAccounts();

        boolean sameOwner = senderAccount.getUserId().equals(recipientAccount.getUserId());

        double comission = sameOwner ? 0 : transferAmount * accountProperties.getTransferComission();

        
        var newSenderAmount = senderAccount.getMoneyAmount() - transferAmount - comission;
        if (newSenderAmount < 0) {
            throw new NegativeMoneyAmountException();
        }

        var newRecipientAmount = recipientAccount.getMoneyAmount() + transferAmount;
        
        senderAccount.setMoneyAmount(newSenderAmount);
        recipientAccount.setMoneyAmount(newRecipientAmount);

        accountRepo.save(senderAccount);
        accountRepo.save(recipientAccount);
        userRepo.save(senderUser);
        if(!sameOwner){
            userRepo.save(recipientUser);
        }

    }


    public Account withdrawAccount(Long accountId, double withdrawAmount){
        if (withdrawAmount < 0){
            throw new NegativeMoneyAmountException();
        }
        var account = accountRepo.findById(accountId).orElseThrow(
            () -> new AccountNotFoundException(accountId)
        );
        var user = userRepo.findById(account.getUserId()).orElseThrow();
        var userAccounts = user.getAccounts();
        int idx = userAccounts.indexOf(account);

        var newAmount = account.getMoneyAmount() - withdrawAmount;

        if(newAmount < 0){
            throw new NegativeMoneyAmountException();
        }

        account.setMoneyAmount(newAmount);
        userAccounts.set(idx, account);

        userRepo.save(user);
        accountRepo.save(account);

        return account;
    }
}
