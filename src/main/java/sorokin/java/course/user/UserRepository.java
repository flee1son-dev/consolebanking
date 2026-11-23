package sorokin.java.course.user;

import org.springframework.stereotype.Repository;
import sorokin.java.course.utils.TransactionHelper;

import java.util.List;

@Repository
public class UserRepository {
    private final TransactionHelper transactionHelper;


    public UserRepository(TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

    public void saveUser(User user){
        transactionHelper.executeInTransactionOrJoin(session -> {
            session.persist(user);
        });
    }

    public User findById(Integer id){
        return transactionHelper.executeInTransactionOrJoin(session -> {
            return session
                    .createSelectionQuery("""
                    select u from User u left join fetch u.accountList
                    where u.id = :userId
                    """, User.class)
                    .setParameter("userId", id)
                    .getSingleResult();
        });
    }

    public List<User> findAll(){
        return transactionHelper.executeInTransactionOrJoin(session -> {
            return session
                    .createSelectionQuery("select u from User u left join fetch u.accountList", User.class)
                    .list();
        });
    }


}
