package com.example.hotels.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.HotelAmenities;

@Repository
public interface HotelAmenitiesRepository extends JpaRepository<HotelAmenities, Long> {
   /*  List<HotelAmenities> findByHotelId(Long hotelId);  */
    
}
