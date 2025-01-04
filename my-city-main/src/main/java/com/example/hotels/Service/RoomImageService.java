package com.example.hotels.Service;

import com.example.hotels.Model.HotelImage;
import com.example.hotels.Model.RoomImage;
import com.example.hotels.Repository.RoomImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomImageService {

    @Autowired
    private RoomImageRepository roomImageRepository;

    // Save a new room image
    public RoomImage saveRoomImage(RoomImage roomImage) {
        return roomImageRepository.save(roomImage);
    }

    // Get all images for a specific room
    public List<RoomImage> getRoomImages(Long roomId) {
        return roomImageRepository.findByRoom_RoomId(roomId);
    }

    // Get all room images
    public List<RoomImage> getAllRoomImages() {
        return roomImageRepository.findAll(); // Fetches all room images
    }
    // Get image by its ID
    public Optional<RoomImage> getRoomImageById(Long imageId) {
        return roomImageRepository.findById(imageId);
    }

    // Delete image by ID
    public void deleteRoomImage(Long imageId) {
        roomImageRepository.deleteById(imageId);
    }


    public List<RoomImage> getRoomImagesByRoomId(Long roomId) {
        return roomImageRepository.findByRoom_RoomId(roomId);
    }


    public RoomImage getPrimaryImageByRoomId(Long roomId) {
        // Fetch the primary image for the hotel
        return roomImageRepository.findFirstByRoom_RoomIdOrderByCreatedAtAsc(roomId);
    }
    
}
