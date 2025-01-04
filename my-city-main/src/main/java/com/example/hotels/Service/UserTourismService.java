package com.example.hotels.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.UserTourism;
import com.example.hotels.Repository.UserTourismRepository;


@Service
public class UserTourismService {

     @Autowired
    private UserTourismRepository userRepository;

    public UserTourism authenticate(String email, String password) {
        UserTourism user = userRepository.findByEmail(email);

        // Check if user exists and password matches (plain text comparison)
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void registerUser(String name, String email, String password, String mobile, String address) {
        UserTourism user = new UserTourism();
        user.setName(name);
        user.setEmail(email.toLowerCase());
        user.setPassword(password);
        user.setMobile(mobile);
        user.setAddress(address);


        userRepository.save(user);
    }

      // Check if an email exists in the repository
      public boolean emailExists(String email) {
        UserTourism user = userRepository.findByEmail(email);
        return user != null;
    }
    
}
