package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
