package com.oneOone.websocketapplication.controllers;


import com.oneOone.websocketapplication.models.Status;
import com.oneOone.websocketapplication.models.User;
import com.oneOone.websocketapplication.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user){

        user.setStatus(Status.ONLINE);
        userRepository.save(user);

    }
    public void disconnect(User user){


        var storeUser = userRepository.findById(user.getNickName())
                .orElse(null);
        if(storeUser != null){
            storeUser.setStatus(Status.OFFLINE);
            userRepository.save(storeUser);
        }

    }

    public List<User> findDisconnedUsers(){
        return userRepository.findAllByStatus(Status.ONLINE);

    }
}
