package com.example.hamromistiri.Service;

import com.example.hamromistiri.Dto.CustomerLoginRequest;
import com.example.hamromistiri.Dto.CustomerSignupRequest;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Repository.CustomerRepository;
import com.example.hamromistiri.Repository.MisitiriDetailRepository;
import com.example.hamromistiri.Repository.ProblemRepository;
import com.example.hamromistiri.Repository.ReviewRepository;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MistiriDetailsService mistiriDetailsService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private  ReviewService reviewService;

    @Autowired
    private EmailSenderService emailSender;

    @Autowired
    private GenerateTokenService generateTokenService;

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
        customer.setIsVerified(false);

        //generating  a verification token
        String token = generateTokenService.generateToken();
        customer.setRandomToken(token);


        customer = customerRepository.saveAndFlush(customer);

        Customer saved = customerRepository.save(customer);

        //send email
        emailSender.sendEmail(saved.getEmail(),
                "Hello " + saved.getFirstName() + ", \n" +
                        "Please click the link below to verify your account. \n" +
                        "http://localhost:8080/verify/customer/" + customer.getId() + "/" + customer.getRandomToken(),
                "Please Verify your account");

        return saved;
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

    public String verify(int id, String token) {
        Customer c1 = customerRepository.findById(id);
        String urlToken = token;
        String dbToken = c1.getRandomToken();

        if (!urlToken.equals(dbToken)) {
            throw new AppException("Token invalid", HttpStatus.FORBIDDEN);
        }

        c1.setIsVerified(true);
        customerRepository.save(c1);
        String message = "Your account is now activated";

        return message;
    }

    public Customer updateCustomer(Customer customer){
        Customer existingCustomer = customerRepository.findById(customer.getId());
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setPhoneNo(customer.getPhoneNo());
        existingCustomer.setLocation(customer.getLocation());

        return customerRepository.save(existingCustomer);
    }

    @Transactional
    public String deleteCustomer(int id){
        mistiriDetailsService.deleteMistiriWhenDeleteCustomer(id);
        problemService.deleteProblemWhenDeleteCustomer(id);
        reviewService.deleteReviewWhenDeleteCustomer(id);
        customerRepository.deleteById(id);
        return "Your account is deleted successfully.";
    }

    public Customer getCustomer(int id){
        return customerRepository.findById(id);
    }

//    public Customer saveCustomer(Customer customer){
//        return customerRepository.save(customer);
//    }

    public List<Customer> getUser() {
        return customerRepository.findAll();
    }

}
