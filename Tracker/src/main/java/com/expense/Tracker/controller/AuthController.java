package com.expense.Tracker.controller;

import com.expense.Tracker.dto.AllUsers;
import com.expense.Tracker.dto.LoginRequest;
import com.expense.Tracker.dto.RegisterRequest;
import com.expense.Tracker.entity.User;
import com.expense.Tracker.repository.UserRepository;
import com.expense.Tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest){
        return userService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @GetMapping("/all")
    public List<AllUsers> getAllUsers(){
        List <AllUsers> list = new ArrayList<>();

        List<User> users = userRepository.findAll();
        for (User user : users){
            var dto = new AllUsers();
            dto.setFullname(user.getFullname());
            dto.setEmail(user.getEmail());
            list.add(dto);
        }
        return list;
    }
}
