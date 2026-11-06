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
            var firstUserAccount = userAccounts.get(0);
            var newAmount = firstUserAccount.getMoneyAmount() + account.getMoneyAmount();
            firstUserAccount.setMoneyAmount(newAmount);
            userAccounts.set(0, firstUserAccount);
        }
        userAccounts.remove(account);
        accountRepo.close(accountId);

        userRepo.save(user);
    }


    public Account depositAccount(Long accountId, double deposit){
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
        if(senderAccountId == recipientAccountId){
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

        int senderIdx = senderUserAccounts.indexOf(senderAccount);
        int recipientIdx = recipientUserAccounts.indexOf(recipientAccount);

        
        var newSenderAmount = senderAccount.getId() == recipientAccount.getId() ? 
                senderAccount.getMoneyAmount() - transferAmount :
                senderAccount.getMoneyAmount() - (transferAmount * accountProperties.getTransferComission());

        var newRecipientAmount = recipientAccount.getMoneyAmount() + transferAmount;
        
        if (newSenderAmount < 0) {
            throw new NegativeMoneyAmountException();
        }
        if (newRecipientAmount < 0) {
            throw new NegativeMoneyAmountException();
        }
        
        
        senderAccount.setMoneyAmount(newSenderAmount);
        recipientAccount.setMoneyAmount(newRecipientAmount);
        senderUserAccounts.set(senderIdx, senderAccount);
        recipientUserAccounts.set(recipientIdx, recipientAccount);


        accountRepo.save(senderAccount);
        accountRepo.save(recipientAccount);
        
        userRepo.save(senderUser);
        userRepo.save(recipientUser);
    }


    public Account withdrawAccount(Long accountId, double withdrawAmount){
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
