package com.example.hamromistiri.Service;

import com.example.hamromistiri.Model.User;
import com.example.hamromistiri.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }


}
