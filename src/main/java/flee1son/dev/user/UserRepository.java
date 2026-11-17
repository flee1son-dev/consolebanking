package flee1son.dev.user;

import flee1son.dev.user.exceptions.UserNotFound;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final Map<Integer, User> userMap = new HashMap<>();


    public void save(User u){
        userMap.put(u.getId(), u);
    }

    public boolean existsByLogin(User u){
        if(userMap.containsValue(u)){
            return true;
        }
        return false;
    }

    public List<User> findAllUsers() {
        return userMap.values().stream().toList();
    }

    public Optional<User> findUserById(int id){
        return Optional.ofNullable(userMap.get(id));
    }
}
