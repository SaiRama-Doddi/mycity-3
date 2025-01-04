package com.example.hotels.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hotels.Model.HotelAmenities;
import com.example.hotels.Service.HotelAmenitiesService;

@RestController
public class HotelAmenitiesController {

    @Autowired
    private HotelAmenitiesService hotelAmenitiesService;

    @PostMapping("/hotel-amenity")
    public HotelAmenities saveHotelAmenity(@RequestBody HotelAmenities hotelAmenity) {
        return hotelAmenitiesService.saveHotelAmenity(hotelAmenity);
    }

    @PutMapping("/hotel-amenity")
    public HotelAmenities updateHotelAmenity(@RequestBody HotelAmenities hotelAmenity) {
        return hotelAmenitiesService.updateHotelAmenity(hotelAmenity);
    }

    @GetMapping("/hotel-amenities")
    public List<HotelAmenities> getAllHotelAmenities() {
        return hotelAmenitiesService.getAllHotelAmenities();
    }

    @GetMapping("/hotel-amenity/{hotelAmenityId}")
    public HotelAmenities getHotelAmenityById(@PathVariable Long hotelAmenityId) {
        return hotelAmenitiesService.getHotelAmenityById(hotelAmenityId);
    }

    @DeleteMapping("/hotel-amenity/{hotelAmenityId}")
    public String deleteHotelAmenity(@PathVariable Long hotelAmenityId) {
        hotelAmenitiesService.deleteHotelAmenity(hotelAmenityId);
        return "Hotel Amenity with id " + hotelAmenityId + " is deleted";
    }
}
