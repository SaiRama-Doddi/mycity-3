package com.example.hotels.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.Amenity;
import com.example.hotels.Repository.AmenityRepository;

@Service
public class AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    public Amenity saveAmenity(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    public Amenity updateAmenity(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    public Amenity getAmenityById(Long amenityId) {
        return amenityRepository.findById(amenityId).orElse(null);
    }
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

   

    public void deleteAmenity(Long amenityId) {
        amenityRepository.deleteById(amenityId);
    }

    public List<Amenity> getAmenitiesByIds(List<Long> amenityIds) {
        return amenityRepository.findAllById(amenityIds); // Fetch amenities by their IDs
    }
    
}

