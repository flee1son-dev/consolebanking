package sorokin.java.course.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
public class TransactionHelper {
    private final SessionFactory sessionFactory;

    public TransactionHelper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public <T> T executeInTransactionOrJoin(Function<Session, T> action) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        boolean owner = !transaction.isActive();

        if (owner) {
            transaction = session.beginTransaction();
        }
        try {
            T result = action.apply(session);
            if (owner) {
                transaction.commit();
            }
            return result;
        } catch (RuntimeException e) {
            if (owner) {
                transaction.rollback();
            }
            throw e;
        }
    }

    public void executeInTransactionOrJoin(Consumer<Session> action){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        boolean owner = !transaction.isActive();

        if(owner){
            transaction = session.beginTransaction();
        }
        try {
            action.accept(session);
            if(owner){
                transaction.commit();
            }
        } catch (RuntimeException e) {
            if(owner){
                transaction.rollback();
            }
            throw e;
        }
    }
}