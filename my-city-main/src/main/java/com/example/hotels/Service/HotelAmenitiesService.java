package com.example.hotels.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.HotelAmenities;
import com.example.hotels.Repository.HotelAmenitiesRepository;

@Service
public class HotelAmenitiesService {

    @Autowired
    private HotelAmenitiesRepository hotelAmenitiesRepository;

    public HotelAmenities saveHotelAmenity(HotelAmenities hotelAmenity) {
        return hotelAmenitiesRepository.save(hotelAmenity);
    }

    public HotelAmenities updateHotelAmenity(HotelAmenities hotelAmenity) {
        return hotelAmenitiesRepository.save(hotelAmenity);
    }

    public HotelAmenities getHotelAmenityById(Long hotelAmenityId) {
        return hotelAmenitiesRepository.findById(hotelAmenityId).orElse(null);
    }

    public List<HotelAmenities> getAllHotelAmenities() {
        return hotelAmenitiesRepository.findAll();
    }

    public void deleteHotelAmenity(Long hotelAmenityId) {
        hotelAmenitiesRepository.deleteById(hotelAmenityId);
    }

   /*  public List<HotelAmenities> getHotelAmenitiesByHotelId(Long hotelId) {
        return hotelAmenitiesRepository.findByHotelId(hotelId);
    } */

}

