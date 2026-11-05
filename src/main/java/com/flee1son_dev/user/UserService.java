package com.flee1son_dev.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.flee1son_dev.user.exceptions.UserAlreadyExistsException;
import com.flee1son_dev.user.exceptions.UserNotFoundException;

@Service 
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }


    public User createUser(
        String login
    ){
        if(userRepo.existsByLogin(login)) {
            throw new UserAlreadyExistsException();
        }
        if(login.isBlank() || login.isEmpty()){
            throw new RuntimeException("User's login cannot be empty");
        }
        var user = new User(
            userRepo.nextId(), 
            login, 
            new ArrayList<>()
        );

        userRepo.save(user);
        return user;
    }

    public User getUserById(Long id){
        var user = userRepo.findById(id).orElseThrow(
            () -> new UserNotFoundException(id)
        );
        return user;
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }



}
