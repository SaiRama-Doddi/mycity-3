package com.example.hotels.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
   
    
}
