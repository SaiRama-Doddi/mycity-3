package com.example.hotels.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.hotels.Model.Hotel;
import com.example.hotels.Model.Room;
import com.example.hotels.Repository.RoomRepository;
import java.util.List;

@Service
public class HotelRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public void assignRoomsToHotel(Hotel hotel, List<Room> rooms) {
        if (rooms != null && !rooms.isEmpty()) {
            rooms.forEach(room -> {
                room.setHotel(hotel);
                room.setCity(hotel.getCity()); // Ensure room has same city as hotel
                roomRepository.save(room);
            });
            hotel.setRooms(rooms);
        }
    }

    @Transactional
    public void updateHotelRooms(Hotel hotel, List<Room> newRooms) {
        // Clear existing rooms
        if (hotel.getRooms() != null) {
            hotel.getRooms().clear();
        }
        
        // Assign new rooms
        assignRoomsToHotel(hotel, newRooms);
    }
}