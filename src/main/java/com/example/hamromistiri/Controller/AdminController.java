package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Dto.AdminLoginRequest;
import com.example.hamromistiri.Dto.ApiResponse;
import com.example.hamromistiri.Model.Admin;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Repository.CustomerRepository;
import com.example.hamromistiri.Repository.MisitiriDetailRepository;
import com.example.hamromistiri.Repository.ProblemRepository;
import com.example.hamromistiri.Repository.ReviewRepository;
import com.example.hamromistiri.Service.AdminService;
import com.example.hamromistiri.Service.MistiriDetailsService;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/loginAdmin")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminLoginRequest request, HttpSession session) throws AppException{
        Admin admin = adminService.loginAdmin(request);
        return ResponseEntity.ok(new ApiResponse(admin,"Logged in successfully"));
    }

    @Transactional
    @DeleteMapping("/deleteCustomerByAdmin/{cid}")
    public ResponseEntity<?> deleteCustomerByAdmin(@PathVariable int cid){
         adminService.deleteCustomerByAdmin(cid);
        return ResponseEntity.ok(new ApiResponse("Success", "Deleted successfully"));
    }


    @Transactional
    @DeleteMapping("/deleteMistiriByAdmin/{mid}")
    public ResponseEntity<?> deleteMistiriByAdmin(@PathVariable int mid){
        adminService.deleteMistiriByAdmin(mid);
        return ResponseEntity.ok(new ApiResponse("Success", "Deleted successfully"));
    }



    @GetMapping("/findallcustomer")
    public List<Customer> findAllCustomer(){
        return  adminService.findALlCustomer();
    }


}
