package com.example.hotels.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hotels.Model.Room;
import com.example.hotels.Service.RoomService;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")  
    public Room saveRooms(@RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    @PutMapping("/room")
    public Room updateRoom(@RequestBody Room room) {
        return roomService.updateRoom(room);
    }

    @GetMapping("/room")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/room/{roomId}")
    public Room getRoomById(@PathVariable Long roomId) {
        return roomService.getRoomById(roomId);
    }

    @DeleteMapping("/room/{roomId}")
    public String deleteRoom(@PathVariable Long roomId) {
        roomService.deleteRoom(roomId);
        return "Room with id " + roomId + " is deleted";
    }
}
