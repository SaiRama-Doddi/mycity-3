package com.example.hotels.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.RoomAmenities;
import com.example.hotels.Repository.RoomAmenitiesRepository;

@Service
public class RoomAmenitiesService {

    @Autowired
    private RoomAmenitiesRepository roomAmenitiesRepository;

    public RoomAmenities saveRoomAmenity(RoomAmenities roomAmenity) {
        return roomAmenitiesRepository.save(roomAmenity);
    }

    public RoomAmenities updateRoomAmenity(RoomAmenities roomAmenity) {
        return roomAmenitiesRepository.save(roomAmenity);
    }

    public RoomAmenities getRoomAmenityById(Long roomAmenityId) {
        return roomAmenitiesRepository.findById(roomAmenityId).orElse(null);
    }

    public List<RoomAmenities> getAllRoomAmenities() {
        return roomAmenitiesRepository.findAll();
    }

    public void deleteRoomAmenity(Long roomAmenityId) {
        roomAmenitiesRepository.deleteById(roomAmenityId);
    }
}

