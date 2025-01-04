package com.example.hotels.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hotels.Model.RoomAmenities;
import com.example.hotels.Service.RoomAmenitiesService;

@RestController
public class RoomAmenitiesController {

    @Autowired
    private RoomAmenitiesService roomAmenitiesService;

    @PostMapping("/room-amenity")
    public RoomAmenities saveRoomAmenity(@RequestBody RoomAmenities roomAmenity) {
        return roomAmenitiesService.saveRoomAmenity(roomAmenity);
    }

    @PutMapping("/room-amenity")
    public RoomAmenities updateRoomAmenity(@RequestBody RoomAmenities roomAmenity) {
        return roomAmenitiesService.updateRoomAmenity(roomAmenity);
    }

    @GetMapping("/room-amenities")
    public List<RoomAmenities> getAllRoomAmenities() {
        return roomAmenitiesService.getAllRoomAmenities();
    }

    @GetMapping("/room-amenity/{roomAmenityId}")
    public RoomAmenities getRoomAmenityById(@PathVariable Long roomAmenityId) {
        return roomAmenitiesService.getRoomAmenityById(roomAmenityId);
    }

    @DeleteMapping("/room-amenity/{roomAmenityId}")
    public String deleteRoomAmenity(@PathVariable Long roomAmenityId) {
        roomAmenitiesService.deleteRoomAmenity(roomAmenityId);
        return "Room Amenity with id " + roomAmenityId + " is deleted";
    }
}
