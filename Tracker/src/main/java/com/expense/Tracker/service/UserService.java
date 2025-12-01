package com.expense.Tracker.service;

import com.expense.Tracker.dto.RegisterRequest;
import com.expense.Tracker.entity.User;
import com.expense.Tracker.jwt.JwtUtil;
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

    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(RegisterRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent())
            return "Email already exists";

        User user = new User();
        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return jwtUtil.generateToken(user.getEmail());
    }

    public String loginUser(String email, String password){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if(passwordEncoder.matches(password, user.getPassword()))
            return jwtUtil.generateToken(email);

        return "Invalid Credentials";
    }
}
