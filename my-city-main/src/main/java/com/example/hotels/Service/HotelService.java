package com.example.hotels.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.hotels.Model.Hotel;
import com.example.hotels.Model.Room;
import com.example.hotels.Repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelRoomService hotelRoomService;


      public void saveHotelWithRooms(Hotel hotel) {
        for (Room room : hotel.getRooms()) {
            room.setHotel(hotel); // Ensure the bidirectional relationship is established
        }
        hotelRepository.save(hotel); // Save the hotel and cascade to rooms
    }

    @Transactional
    public Hotel saveHotel(Hotel hotel) {
        if (hotel.getRooms() == null) {
            hotel.setRooms(new ArrayList<>());
        }
        if (hotel.getAmenities() == null) {
            hotel.setAmenities(new ArrayList<>());
        }
    
        Hotel savedHotel = hotelRepository.save(hotel);
        hotelRoomService.assignRoomsToHotel(savedHotel, hotel.getRooms()); // Make sure rooms are correctly assigned
    
        return savedHotel;
    }
    
    @Transactional
    public Hotel updateHotel(Hotel hotel) {
        Hotel existingHotel = hotelRepository.findById(hotel.getHotelId())
            .orElseThrow(() -> new RuntimeException("Hotel not found"));
            
        // Update basic properties
        existingHotel.setName(hotel.getName());
        existingHotel.setDescription(hotel.getDescription());
        existingHotel.setRating(hotel.getRating());
        existingHotel.setContactInfo(hotel.getContactInfo());
        existingHotel.setMapSource(hotel.getMapSource());
        existingHotel.setCity(hotel.getCity());
        existingHotel.setAddress(hotel.getAddress());
        
        // Update relationships
        if (hotel.getRooms() != null) {
            hotelRoomService.updateHotelRooms(existingHotel, hotel.getRooms());
        }
        
        return hotelRepository.save(existingHotel);
    }

    public Hotel getHotelById(Long hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAllWithAddress();
    }

    public void deleteHotel(Long hotelId) {
        hotelRepository.deleteById(hotelId);
    }

    public List<Hotel> searchHotels(String query) {
        return hotelRepository.findAll().stream()
            .filter(hotel -> hotel.getName().toLowerCase().contains(query.toLowerCase()) ||
                           (hotel.getAddress() != null && 
                            hotel.getAddress().getAddressLine2().toLowerCase().contains(query.toLowerCase())))
            .collect(Collectors.toList());
    }
}