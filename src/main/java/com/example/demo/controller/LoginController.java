package com.example.demo.controller;

import com.example.demo.repository.dto.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping()
public class LoginController {
    /*First page after visiting launching server*/
    @GetMapping()
    public String homePage(){
        return "home";
    }

    /*Login page */
    @GetMapping("/login")
    public String authorization(){
        return "login";
    }

    /*Home page if presses logout*/
    @GetMapping("/logout")
    public String logOut(){
        return "home";
    }


}
