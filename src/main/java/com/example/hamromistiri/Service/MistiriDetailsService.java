package com.example.hamromistiri.Service;

import com.example.hamromistiri.Dto.MistiriSignupRequest;
import com.example.hamromistiri.Dto.MistiriLoginRequest;
import com.example.hamromistiri.Dto.PasswordMatcher;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Repository.CustomerRepository;
import com.example.hamromistiri.Repository.MisitiriDetailRepository;
import com.example.hamromistiri.Repository.ReviewRepository;
import com.example.hamromistiri.exception.AppException;
import com.example.hamromistiri.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class MistiriDetailsService {

    @Autowired
    private MisitiriDetailRepository misitiriDetailRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private EmailSenderService emailSender;

    @Autowired
    private GenerateTokenService generateTokenService;

    @Autowired
    private ProblemService problemService;


    @Transactional
    public MistiriDetail registerMistiri(MistiriSignupRequest request) throws AppException {

        Customer c = customerRepository.findByEmail(request.getEmail()).orElse(null);

        if (c != null) {
            throw new AppException("This email is already taken.", HttpStatus.BAD_REQUEST);
        }

        //save multipart file from request to a location and
        // store the url of that location in string variable
        String fileLocation = fileUploadService.storeFile(request.getDocument());

        Customer customer = new Customer();

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());

        String encryptedPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        customer.setPassword(encryptedPassword);
        //password encryption remaining
        customer.setEmail(request.getEmail());
        customer.setPhoneNo(request.getPhoneNo());
        customer.setRole("Mistiri");


        customer.setLocation(request.getLocation());
        customer.setIsVerified(false);


        customer = customerRepository.saveAndFlush(customer);

        MistiriDetail mistiri = new MistiriDetail();

        mistiri.setCustomer(customer);
        mistiri.setService(request.getService());
        mistiri.setAboutYou(request.getAboutYou());
        mistiri.setAvailableStatus(request.getAvailableStatus());
        mistiri.setEmployeeStatus(request.getEmployeeStatus());
        mistiri.setPanNo(request.getPanNo());
        mistiri.setRating(0.0d);
        mistiri.setCount(0);


        MistiriDetail saved = misitiriDetailRepository.save(mistiri);

        //generating  a verification token
        String token = generateTokenService.generateToken();
        customer.setRandomToken(token);

        //sending email
        emailSender.sendEmail(saved.getCustomer().getEmail(),
                "Hello " + saved.getCustomer().getFirstName() + ", \n" +
                        "Please click the link below to verify your account. \n" +
                        "http://localhost:8080/verify/mistiri/" + saved.getCustomer().getId() + "/" + saved.getCustomer().getRandomToken(),
                "Please Verify your account");

        return saved;
    }

    public Customer loginMistiri(MistiriLoginRequest request) throws AppException {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException("User not found for this email", HttpStatus.BAD_REQUEST));

        String encryptedPassword = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        String dbPassword = customer.getPassword();

        if (!encryptedPassword.equals(dbPassword)) {
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


    public MistiriDetail updateMistiri(MistiriDetail mistiri) {
        System.out.println(mistiri.getId());
        MistiriDetail existingMistiri = misitiriDetailRepository.findById(mistiri.getId()).orElse(null);

        if (existingMistiri == null) {
            throw new AppException("Mistiri not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Customer customer = existingMistiri.getCustomer();
        customer.setFirstName(mistiri.getCustomer().getFirstName());
        customer.setLastName(mistiri.getCustomer().getLastName());
        customer.setLocation(mistiri.getCustomer().getLocation());
        customerRepository.save(customer);

        existingMistiri.setPanNo(mistiri.getPanNo());
        existingMistiri.setAboutYou(mistiri.getAboutYou());
        existingMistiri.setAvailableStatus(mistiri.getAvailableStatus());

        return misitiriDetailRepository.save(existingMistiri);

    }

    public Optional<MistiriDetail> getMistiri(int id) {
        return misitiriDetailRepository.findById(id);
    }

    public MistiriDetail getMistiriByCostumerId(int id) {
        return misitiriDetailRepository.findByCustomerId(id);
    }

    public List<MistiriDetail> findAll() {
        return misitiriDetailRepository.findAll();
    }

    public MistiriDetail findById(int id) {
        return misitiriDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mistiri not found with id " + id));
    }

    public MistiriDetail addMistiri(MistiriDetail mistiriDetail) {

        //this is for rating purpose!!! Don't edit this code.
        MistiriDetail mistiriDetail1 = new MistiriDetail();
        mistiriDetail1.setCustomer(mistiriDetail.getCustomer());
        mistiriDetail1.setRating(mistiriDetail.getRating());
        mistiriDetail1.setService(mistiriDetail.getService());
        mistiriDetail1.setAboutYou(mistiriDetail.getAboutYou());
        mistiriDetail1.setAvailableStatus(mistiriDetail.getAvailableStatus());
        mistiriDetail1.setEmployeeStatus(mistiriDetail.getEmployeeStatus());
        mistiriDetail1.setPanNo(mistiriDetail.getPanNo());
        mistiriDetail1.setCount(0);
        return misitiriDetailRepository.save(mistiriDetail);
    }

    public List<MistiriDetail> findByMistiri(String service, String address) {
        List<MistiriDetail> mistiriDetails = new ArrayList<>();
        mistiriDetails.addAll(misitiriDetailRepository.findAvailableMistiri(service, address));
//        mistiriDetails.addAll(misitiriDetailRepository.findNotByAddress(service, address));
        return mistiriDetails;
    }

    public MistiriDetail showReview(int id) {
        return misitiriDetailRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Mistiri not found with id " + id));
    }

    public List<MistiriDetail> findByService(String service) {
        return misitiriDetailRepository.findByServices(service);
    }


    public MistiriDetail findByaId(Integer id) {
        return misitiriDetailRepository.findAvailableMistiriId(id);
    }


    public void deleteMistiriWhenDeleteCustomer(int id) throws AppException{
        misitiriDetailRepository.deleteMistiriByCustomerId(id);
    }

    @Transactional
    public String deleteMistiri(int id, PasswordMatcher passwordMatcher) {
        MistiriDetail mistiri = misitiriDetailRepository.findByCustomerId(id);
        String encryptedPassword = DigestUtils.md5DigestAsHex(passwordMatcher.getPassword().getBytes());

        if (!encryptedPassword.equals(mistiri.getCustomer().getPassword())) {
            throw new AppException("Invalid Password", HttpStatus.FORBIDDEN);
        }
        int mid = mistiri.getId();
        problemService.deleteProblemWhenDeleteMistiri(mid);
        problemService.deleteProblemWhenDeleteCustomer(id);
        reviewRepository.deleteReviewByMistiriId(mid);
        reviewRepository.deleteReviewByCustomerId(id);
        customerRepository.deleteById(id);
        misitiriDetailRepository.deleteById(mid);
        return "Your account is deleted successfully";
    }
}