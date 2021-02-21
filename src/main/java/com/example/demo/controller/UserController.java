package com.example.demo.controller;

import com.example.demo.repository.dto.User;
import com.example.demo.service.BookService;
import com.example.demo.service.BookingService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class UserController {
    private UserService userService;
    private BookService bookService;
    private BookingService bookingService;

    @Autowired
    public UserController(UserService userService, BookService bookService, BookingService bookingService) {
        this.userService = userService;
        this.bookService = bookService;
        this.bookingService = bookingService;
    }

    @GetMapping()
    public String indexPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserById(username);
        user.setPassword(null);

        model.addAttribute("user", user);
        model.addAttribute("books",bookService.allBooks());
        model.addAttribute("users",userService.allUsers());
        if (user.getRole().getRolename().equals("ROLE_ADMIN")){
            return "admin";
        }
        return "index";
    }
}
