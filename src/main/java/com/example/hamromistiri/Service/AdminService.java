package com.example.hamromistiri.Service;

import com.example.hamromistiri.Dto.AdminLoginRequest;
import com.example.hamromistiri.Model.Admin;
import com.example.hamromistiri.Repository.AdminRepository;
import com.example.hamromistiri.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

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

}
