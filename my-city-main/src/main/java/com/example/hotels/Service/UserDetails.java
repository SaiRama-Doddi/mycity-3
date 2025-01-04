package com.example.hotels.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.UserTourism;
import com.example.hotels.Repository.UserDetailsRepository;


@Service
public class UserDetails {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserTourism getUserDetails(String email, String password){
        return userDetailsRepository.findByEmailAndPassword(email, password);
    }
}
