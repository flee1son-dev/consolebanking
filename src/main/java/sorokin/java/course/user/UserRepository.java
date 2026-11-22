package sorokin.java.course.user;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveUser(User user){
        sessionFactory.getCurrentSession().persist(user);
    }

    public User findById(Long id){
        return sessionFactory.getCurrentSession().find(User.class, id);
    };

    public List<User> findAll(){
        return sessionFactory.getCurrentSession()
                .createSelectionQuery("select u from User u", User.class)
                .list();
    }


}
