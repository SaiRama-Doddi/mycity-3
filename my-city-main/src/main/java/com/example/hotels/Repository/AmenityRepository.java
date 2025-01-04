package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Amenity;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    
}
