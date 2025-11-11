package com.expense.Tracker.service;

import com.expense.Tracker.entity.User;
import com.expense.Tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isPresent())
            return "Email already exists";
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Registered successfully";
    }

    public String loginUser(String email, String password){
        User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User Not Found"));

        if(passwordEncoder.matches(password, user.getPassword()))
            return "Login Successful";
        else
            return "Invalid credentials";


    }
}
