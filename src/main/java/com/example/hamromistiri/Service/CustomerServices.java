package com.example.hamromistiri.Service;

import com.example.hamromistiri.Dto.CustomerLoginRequest;
import com.example.hamromistiri.Dto.CustomerSignupRequest;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Repository.CustomerRepository;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(CustomerSignupRequest request) throws AppException {

        Customer c = customerRepository.findByEmail(request.getEmail()).orElse(null);

        if (c != null) {
            throw new AppException("This email is already taken.", HttpStatus.BAD_REQUEST);
        }


        Customer customer = new Customer();

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());

//        customer.setPassword(request.getPassword());

        // password encrypted and saved in database
        String encryptedPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        customer.setPassword(encryptedPassword);

        customer.setEmail(request.getEmail());
        customer.setPhoneNo(request.getPhoneNo());
        customer.setRole("Customer");
        customer.setLocation(request.getLocation());

        customer = customerRepository.saveAndFlush(customer);

        return customerRepository.save(customer);
    }

    public Customer loginCustomer(CustomerLoginRequest request) throws AppException {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException("User not found for this email", HttpStatus.BAD_REQUEST));

        String requestPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        String dbPassword = customer.getPassword();

        if (!requestPassword.equals(dbPassword)) {
            throw new AppException("Invalid Password", HttpStatus.FORBIDDEN);
        }
        // if we throw exception then rest of the code doesn't run in a method
        return customer;
    }

//    public Customer saveCustomer(Customer customer){
//        return customerRepository.save(customer);
//    }

    public List<Customer> getUser(){
        return customerRepository.findAll();
    }


}
