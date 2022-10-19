package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Dto.CustomerLoginRequest;
import com.example.hamromistiri.Dto.CustomerSignupRequest;
import com.example.hamromistiri.Dto.MistiriLoginRequest;
import com.example.hamromistiri.Dto.MistiriSignupRequest;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Service.CustomerServices;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;

    @PostMapping("/registerCustomer")
    public Customer signUpCustomer(@Valid @RequestBody CustomerSignupRequest request) throws AppException {
        return customerServices.registerCustomer(request);
    }

    @PostMapping("/loginCustomer")
    public ResponseEntity<?> loginCustomer(@RequestBody CustomerLoginRequest request, HttpSession session) throws AppException {
        Customer customer = customerServices.loginCustomer(request);
        session.setAttribute("id", customer.getId());
        session.setAttribute("email", customer.getEmail());
        session.setAttribute("firstName", customer.getFirstName());
        session.setAttribute("lastName", customer.getLastName());

        return ResponseEntity.ok("Logged in Successfully.");
    }

//    @PostMapping("/registerNewCustomer")
//    public Customer registerNewCustomer(@RequestBody Customer customer) {
//        return customerServices.saveCustomer(customer);
//    }

//    @GetMapping("/findCustomer")
//    public List<Customer> getCustomer(){
//        return customerServices.getCustomer();
//    }

}
