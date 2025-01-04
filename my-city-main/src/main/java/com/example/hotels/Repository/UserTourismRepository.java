package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.UserTourism;


@Repository
public interface UserTourismRepository  extends JpaRepository<UserTourism,Integer> {
    UserTourism findByEmail(String email);
    UserTourism findByMobile(String mobile);
    
}
