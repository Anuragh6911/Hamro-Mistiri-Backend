package com.example.hamromistiri.Controller;

import com.example.hamromistiri.Dto.AdminLoginRequest;
import com.example.hamromistiri.Dto.ApiResponse;
import com.example.hamromistiri.Dto.CustomerLoginRequest;
import com.example.hamromistiri.Model.Admin;
import com.example.hamromistiri.Model.Customer;
import com.example.hamromistiri.Service.AdminService;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/loginAdmin")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminLoginRequest request, HttpSession session) throws AppException{
        Admin admin = adminService.loginAdmin(request);
        return ResponseEntity.ok(new ApiResponse(admin,"Logged in successfully"));
    }

}
