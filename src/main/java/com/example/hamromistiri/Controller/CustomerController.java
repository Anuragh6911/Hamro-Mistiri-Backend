package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Dto.ApiResponse;
import com.example.hamromistiri.Dto.CustomerDto;
import com.example.hamromistiri.Dto.CustomerLoginRequest;
import com.example.hamromistiri.Dto.CustomerSignupRequest;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Service.CustomerServices;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin("*") //.env file limited allowed origins
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

        return ResponseEntity.ok(new ApiResponse(customer,"Logged in successfully"));
    }
    @GetMapping("/verify/customer/{id}/{token}")
    public String verifyCustomer(@PathVariable int id,
                                             @PathVariable String token){
        return customerServices.verify(id,token);
    }

    @PutMapping("/updateCustomer")
    public String updateCustomerData(@RequestBody Customer customer) throws AppException{
        customerServices.updateCustomer(customer);
        return "Your profile is updated successfully.";
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable int id){
        return customerServices.deleteCustomer(id);
    }

    @GetMapping("customerDashboard/{id}")
    public Customer getCustomer(@PathVariable int id){
        return customerServices.getCustomer(id);
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
