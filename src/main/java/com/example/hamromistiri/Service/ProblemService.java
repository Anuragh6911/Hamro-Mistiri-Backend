package com.example.hamromistiri.Service;

import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Model.Problem;
import com.example.hamromistiri.Repository.CustomerRepository;
import com.example.hamromistiri.Repository.MisitiriDetailRepository;
import com.example.hamromistiri.Repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Problem saveProblem(Problem problem, int uid, int mid) {
        Problem saved = problemRepository.save(problem);
        Customer customer = customerRepository.findById(uid);
        Optional<MistiriDetail> mistiri = mistiriRepository.findById(mid);

        //sending sms
        String m1 = "Hello Sir, " + customer.getFirstName() + " " + customer.getLastName() + " hired you. \n" + "Problem Description: " + saved.getDescription() + "\n" +
                "Urgency: " + saved.getUrgency() + "\nProblem Id: #" + saved.getId() + "\n" + "Customer Phone Number : " + customer.getPhoneNo() +
                "\nPlease contact the customer.\n" +
                "- Hamro Mistiri";
        smsService.sendSms(mistiri.get().getCustomer().getPhoneNo(), m1);

        return saved;
    }

    public Problem displayProblem(int id) {
        return problemRepository.findById(id).orElse(null);
    }
}
