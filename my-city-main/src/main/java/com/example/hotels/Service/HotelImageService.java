package com.example.hotels.Service;



import com.example.hotels.Model.Hotel;
import com.example.hotels.Model.HotelImage;
import com.example.hotels.Repository.HotelImageRepository;
import com.example.hotels.Repository.HotelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelImageService {

    @Autowired
    private HotelImageRepository hotelImageRepository;

    @Autowired
    private HotelRepository hotelRepository;


    public HotelImage getPrimaryImageByHotelId(Long hotelId) {
        // Fetch the primary image for the hotel
        return hotelImageRepository.findFirstByHotel_HotelIdOrderByCreatedAtAsc(hotelId);
    }


    public HotelImage saveHotelImageCreated(HotelImage hotelImage) {
        // Set the createdAt timestamp if it's not already set
        if (hotelImage.getCreatedAt() == null) {
            hotelImage.setCreatedAt(new java.util.Date()); // Current timestamp
        }
        return hotelImageRepository.save(hotelImage);
    }

    // Save a new hotel image
    public HotelImage saveHotelImage(HotelImage hotelImage) {
        return hotelImageRepository.save(hotelImage);
    }

    // Get all images for a specific hotel
    public List<HotelImage> getHotelImages(Long hotelId) {
        return hotelImageRepository.findByHotel_HotelId(hotelId);
    }

    // Get all hotel images
    public List<HotelImage> getAllHotelImages() {
        return hotelImageRepository.findAll();
    }

    // Get image by its ID
    public Optional<HotelImage> getHotelImageById(Long hotelImageId) {
        return hotelImageRepository.findById(hotelImageId);
    }

    // Delete image by ID
    public void deleteHotelImage(Long hotelImageId) {
        hotelImageRepository.deleteById(hotelImageId);
    }

   /*  public List<Hotel> getHotelIds() {
        return hotelRepository.findId();
    } */

    public List<HotelImage> getHotelImagesByHotelId(Long hotelId) {
        return hotelImageRepository.findByHotel_HotelId(hotelId);
    }

}

