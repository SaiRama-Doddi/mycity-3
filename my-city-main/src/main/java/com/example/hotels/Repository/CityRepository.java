package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    
}
