package sorokin.java.course.account;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import sorokin.java.course.utils.TransactionHelper;

import java.util.List;

@Repository
public class AccountRepository {
    private final TransactionHelper transactionHelper;

    public AccountRepository(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public void saveAccount(Account account){
        transactionHelper.executeInTransactionOrJoin(session -> {
            session.persist(account);
        });
    }

    public  Account findById(Integer id){
        return transactionHelper.executeInTransactionOrJoin(session -> {
            return session
                    .createSelectionQuery(
                            """
                            select a from Account a left join fetch a.user
                            where a.id = :accountId
                            """, Account.class)
                    .setParameter("accountId", id)
                    .getSingleResult();
        });
    }

    public List<Account> findAll(Integer userId){
        return transactionHelper.executeInTransactionOrJoin(session -> {
            return session
                    .createSelectionQuery("""
                            select a from Account a left join fetch a.user
                            where a.user.id = :userId
                            """, Account.class)
                    .setParameter("userId", userId)
                    .getResultList();
        });
    }

    public void updateAccount(Account account){
        transactionHelper.executeInTransactionOrJoin(session -> {
            return session.merge(account);
        });
    }

    public void removeAccount(Integer id){
        transactionHelper.executeInTransactionOrJoin(session -> {
            var accountToDelete = session
                    .createSelectionQuery("""
                            select a from Account a
                            left join fetch a.user
                            where a.id = :accountId
                            """, Account.class)
                    .setParameter("accountId", id)
                    .getSingleResult();

            var user = accountToDelete.getUser();
            if (user == null){
                throw new EntityNotFoundException(
                        "Account with id " + accountToDelete.getId() + " has no associated user"
                    );
                }
            user.getAccountList().remove(accountToDelete);
            session.remove(accountToDelete);
        });
    }
}
