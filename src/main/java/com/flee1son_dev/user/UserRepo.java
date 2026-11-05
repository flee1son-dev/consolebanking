package com.flee1son_dev.user;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository 
public class UserRepo {
    private final Map<Long, User> usersById = new LinkedHashMap<>();
    private final Map<String, User> usersByLogin = new HashMap<>();

    private final AtomicLong idGen = new AtomicLong();

    public long nextId(){
        return idGen.incrementAndGet();
    }


    public void save(User u){
        usersById.put(u.getId(), u);
        usersByLogin.put(u.getLogin(), u);

    }

    public Boolean existsByLogin(String login){
        if(usersByLogin.containsKey(login)){
            return true;
        }
        return false;
    }

    public Optional<User> findById(Long id){
        return Optional.ofNullable(usersById.get(id));
    }

    public List<User> findAll(){
        return List.copyOf(usersById.values());
    }

}
