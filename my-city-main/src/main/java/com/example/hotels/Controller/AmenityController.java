package com.example.hotels.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hotels.Model.Amenity;
import com.example.hotels.Service.AmenityService;

@RestController
public class AmenityController {

    @Autowired
    private AmenityService amenityService;

    @PostMapping("/amenity")
    public Amenity saveAmenity(@RequestBody Amenity amenity) {
        return amenityService.saveAmenity(amenity);
    }

    @PutMapping("/amenity")
    public Amenity updateAmenity(@RequestBody Amenity amenity) {
        return amenityService.updateAmenity(amenity);
    }

    @GetMapping("/amenities")
    public List<Amenity> getAllAmenities() {
        return amenityService.getAllAmenities();
    }

    @GetMapping("/amenity/{amenityId}")
    public Amenity getAmenityById(@PathVariable Long amenityId) {
        return amenityService.getAmenityById(amenityId);
    }

    @DeleteMapping("/amenity/{amenityId}")
    public String deleteAmenity(@PathVariable Long amenityId) {
        amenityService.deleteAmenity(amenityId);
        return "Amenity with id " + amenityId + " is deleted";
    }
}
