package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;
    public User getUser(String username,String password){
        if(userRepository.findByUsernameAndPassword(username,password)==null)
            return null;
        else
            return userRepository.findByUsernameAndPassword(username,password);
    }
    @Transactional
    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

}
