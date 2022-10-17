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

    @PostMapping("/registerNewCustomer")
    public Customer registerNewCustomer(@RequestBody Customer customer) {
        return customerServices.saveCustomer(customer);
    }

//    @GetMapping("/findCustomer")
//    public List<Customer> getCustomer(){
//        return customerServices.getCustomer();
//    }

}
