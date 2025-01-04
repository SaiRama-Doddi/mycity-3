package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    
}
