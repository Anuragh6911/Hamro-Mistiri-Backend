package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Model.User;
import com.example.hamromistiri.Service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/addUser")
    public HttpStatus savingUser(@RequestBody User saveuser) {
        userServices.saveUser(saveuser);
        return HttpStatus.OK;
    }

    @GetMapping("/findUsers")
    public List<User> findUser(){
        return userServices.getUser();
    }

}
