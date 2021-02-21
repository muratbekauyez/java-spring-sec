package com.example.demo.controller;

import com.example.demo.repository.dto.User;
import com.example.demo.service.BookService;
import com.example.demo.service.BookingService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private BookService bookService;
    private BookingService bookingService;

    @Autowired
    public AdminController(UserService userService, BookService bookService, BookingService bookingService) {
        this.userService = userService;
        this.bookService = bookService;
        this.bookingService = bookingService;
    }

    @GetMapping("/")
    public String adminPage(Model model){
        model.addAttribute("books",bookService.allBooks());
        model.addAttribute("users",userService.allUsers());

        return "admin";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam(name = "username")String username,@RequestParam(name = "name")String name,
                          @RequestParam(name = "surname")String surname,@RequestParam(name = "password")String password) {
        User user = new User();
        user.setUsername(username);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        userService.addUser(user);
        return "admin";
    }


}
