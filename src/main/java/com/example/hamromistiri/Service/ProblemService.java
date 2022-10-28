package com.example.hamromistiri.Service;

import com.example.hamromistiri.Model.Problem;
import com.example.hamromistiri.Repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProblemService {
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired private SmsService smsService;
    public Problem saveProblem(Problem problem ){
        Problem saved = problemRepository.save(problem);

        // sending sms
        String message = "Hello, Sir "+saved.getCustomer().getFirstName()+" "+saved.getCustomer().getLastName()+" hired you. Please check your email. ";
        smsService.sendSms(saved.getMistiriDetail().getCustomer().getPhoneNo(),message);

        return saved;
    }

    public Problem displayProblem(int id){
        return problemRepository.findById(id).orElse(null);
    }
}
