package com.example.hamromistiri.Service;

import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Problem;
import com.example.hamromistiri.Repository.CustomerRepository;
import com.example.hamromistiri.Repository.MisitiriDetailRepository;
import com.example.hamromistiri.Repository.ProblemRepository;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MisitiriDetailRepository mistiriRepository;

    @Autowired
    private SmsService smsService;

    @Autowired
    private EmailSenderService emailSender;

    public Problem saveProblem(Problem problem, int uid, int mid) {
        Problem saved = new Problem();

        Customer customer = customerRepository.findById(uid);
        MistiriDetail mistiri = mistiriRepository.findById(mid).orElse(null);

        saved.setCustomer(customer);
        saved.setMistiriDetail(mistiri);
        saved.setDescription(problem.getDescription());
        saved.setUrgency(problem.getUrgency());

        problemRepository.save(saved);

        //sending sms to mistiri
        String msgMistiri = "Hello " + mistiri.getCustomer().getFirstName() + "Ji, \n" + customer.getFirstName() + " " + customer.getLastName() + " hired you. \n" + "Problem Description: " + saved.getDescription() + "\n" +
                "Urgency: " + saved.getUrgency() + "\nProblem Id: #" + saved.getId() + "\n" + "Customer Phone Number : " + customer.getPhoneNo() +
                "\nPlease contact the customer.\n" +
                "- Hamro Mistiri";
        smsService.sendSms(mistiri.getCustomer().getPhoneNo(), msgMistiri);

        //sending mail to user
        emailSender.sendEmail(customer.getEmail(),
                "Hello " +customer.getFirstName()+ " Ji, \n" + "You have hired a professional. Please wait for them to contact you." +
                        " \n" + "Problem Id: #" + saved.getId()+"\n-Hamro Mistiri",
                "Your enquires have been sent.");

        return saved;
    }

    public Problem displayProblem(int id) {
        return problemRepository.findById(id).orElse(null);
    }

    public List<Problem> viewMistiriHistory(int mid) {
        return problemRepository.findProblemByMistiriId(mid);
    }

    public List<Problem> viewCustomerHistory(int uid) {
        return  problemRepository.findProblemByCustomerId(uid);
    }

    public void deleteProblemWhenDeleteCustomer(int id) throws AppException {
        problemRepository.deleteProblemByCustomerId(id);
    }

    public void deleteProblemWhenDeleteMistiri(int id){
        problemRepository.deleteProblemByMistiriId(id);
    }
}
