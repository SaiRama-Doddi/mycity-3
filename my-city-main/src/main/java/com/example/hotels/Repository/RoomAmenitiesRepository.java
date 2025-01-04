package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.RoomAmenities;

@Repository
public interface RoomAmenitiesRepository extends JpaRepository<RoomAmenities, Long> {
    
}
