package com.example.hotels.Repository;



import com.example.hotels.Model.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelImageRepository extends JpaRepository<HotelImage, Long> {

    List<HotelImage> findByHotel_HotelId(Long hotelId);  // Fetch images by hotel ID

    HotelImage findFirstByHotel_HotelIdOrderByCreatedAtAsc(Long hotelId);


    

}
