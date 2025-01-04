package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.UserTourism;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserTourism, Integer> {
    UserTourism findByEmailAndPassword(String email, String password);
}
