package com.example.hotels.Service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.hotels.Model.Hotel;
import com.example.hotels.Model.Room;
import com.example.hotels.Repository.HotelRepository;
import com.example.hotels.Repository.RoomRepository;
import com.example.hotels.dto.RoomDTO;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;


    @Autowired
    private HotelRepository hotelRepository;

    



  


    public List<Room> getRoomsByIds(List<Long> roomIds) {
        return roomRepository.findAllById(roomIds);
    }
    
    


    public List<Room> getRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelHotelId(hotelId);
    }

 
    public List<Room> findAvailableRoomsByHotelAndGuests(Long hotelId, int personCount, String checkInDate, String checkOutDate) {
        LocalDate checkIn = LocalDate.parse(checkInDate);
        LocalDate checkOut = LocalDate.parse(checkOutDate);

        return roomRepository.findAvailableRoomsByHotelAndGuests(hotelId, personCount, checkIn, checkOut);
    }

   public List<RoomDTO> findAvailableRooms(int personCount, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> rooms = roomRepository.findAvailableRooms(personCount, checkInDate, checkOutDate);
        return rooms.stream()
                   .map(this::convertToDTO)
                   .collect(Collectors.toList());
    } 

   private RoomDTO convertToDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setRoomId(room.getRoomId());
        dto.setRoomType(room.getRoomType());
        dto.setPricePerNight(room.getPricePerNight());
        dto.setMaxGuests(room.getMaxGuests());
        dto.setIsAvailable(room.getIsAvailable());
      
        dto.setPrimaryRoomImageBase64(room.getPrimaryRoomImageBase64());
        if (room.getHotel() != null) {
            dto.setHotelName(room.getHotel().getName());
        }
        return dto;
    } 




    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteById(roomId);
    }
     // Method to get a hotel by its ID
   
}

