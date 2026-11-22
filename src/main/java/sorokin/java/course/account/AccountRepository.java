package sorokin.java.course.account;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    private final SessionFactory sessionFactory;

    public AccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Account account){
        sessionFactory.getCurrentSession().persist(account);
    }

    public  Account findById(Long id){
        return sessionFactory.getCurrentSession().find(Account.class, id);
    }
}
