package com.example.hamromistiri.Service;

import com.example.hamromistiri.Dto.AdminLoginRequest;
import com.example.hamromistiri.Model.Admin;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Model.MistiriDetail;
import com.example.hamromistiri.Repository.*;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MisitiriDetailRepository misitiriDetailRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public Admin loginAdmin(AdminLoginRequest request){

        Admin existingAdmin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException("Admin not found for this email", HttpStatus.BAD_REQUEST));

        System.out.println(existingAdmin.getName());

        String loginPassword = request.getPassword();
        String dbPassword = existingAdmin.getPassword();


        if (!loginPassword.equals(dbPassword)) {
            throw new AppException("Invalid Password", HttpStatus.FORBIDDEN);
        }
        // if we throw exception then rest of the code doesn't run in a method
        return existingAdmin;

    }

    public void deleteCustomerByAdmin(int cid) {
        misitiriDetailRepository.deleteMistiriByCustomerId(cid);
        reviewRepository.deleteReviewByCustomerId(cid);
        problemRepository.deleteProblemByCustomerId(cid);
        customerRepository.deleteById(cid);
    }


    public void deleteMistiriByAdmin(int mid) {{

        MistiriDetail mistiri = misitiriDetailRepository.findById(mid).orElse(null);
        int customerId = mistiri.getCustomer().getId();
        System.out.println(customerId);
        problemRepository.deleteProblemByMistiriId(mid);
        reviewRepository.deleteReviewByMistiriId(mid);
        misitiriDetailRepository.deleteById(mid);

        problemRepository.deleteProblemByCustomerId(customerId);
        reviewRepository.deleteReviewByCustomerId(customerId);
//        customerRepository.deleteById(customerId);

    }
    }
}
