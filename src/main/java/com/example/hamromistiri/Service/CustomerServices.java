package com.example.hamromistiri.Service;

import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveUser(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> getUser(){
        return customerRepository.findAll();
    }


}
