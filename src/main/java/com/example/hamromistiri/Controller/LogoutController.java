package com.example.hamromistiri.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String userLogout(HttpSession session){

        session.invalidate();
        return "Logged Out Successfully";
    }
}
