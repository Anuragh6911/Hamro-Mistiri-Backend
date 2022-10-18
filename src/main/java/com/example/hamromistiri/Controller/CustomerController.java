package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Service.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;

    @PostMapping("/addUser")
    public HttpStatus savingUser(@RequestBody Customer saveuser) {
        customerServices.saveUser(saveuser);
        return HttpStatus.OK;
    }

    @GetMapping("/findUsers")
    public List<Customer> findUser(){
        return customerServices.getUser();
    }

}
