package com.example.hotels.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.address")
    List<Hotel> findAllWithAddress();

 /*    List<Hotel> findId(); */

  // Custom method to find by ID, though JpaRepository already provides findById
@Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.rooms WHERE h.id = :id")
Optional<Hotel> findHotelWithRoomsById(@Param("id") Long id);

    
}
