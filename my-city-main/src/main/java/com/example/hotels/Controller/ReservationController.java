package com.example.hotels.Controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotels.Model.Booking;
import com.example.hotels.Model.Room;
import com.example.hotels.Model.RoomImage;
import com.example.hotels.Service.BookingService;
import com.example.hotels.Service.RoomImageService;
import com.example.hotels.Service.RoomService;
import com.example.hotels.dto.ReservationDTO;

@Controller
@RequestMapping("/reserves")
public class ReservationController {



    @Autowired
    private RoomService roomService;


    @Autowired
    private RoomImageService roomImageService;


    @Autowired
    private BookingService bookingService;

    @PostMapping
    public String confirmReservation(@RequestParam Long roomId, 
                                     @RequestParam String roomType,
                                     @RequestParam double price,
                                     @RequestParam int maxGuests,
                                     @RequestParam String checkinDate,
                                     @RequestParam String checkoutDate,
                                 
                                     Model model) {
        Room room = roomService.getRoomById(roomId); // Retrieve room details dynamically
    
     
    
       
        // Set room image if available
        RoomImage primaryImage = roomImageService.getPrimaryImageByRoomId(roomId);
        if (primaryImage != null && primaryImage.getRoomImage() != null) {
            String base64Image = Base64.getEncoder().encodeToString(primaryImage.getRoomImage());
            room.setPrimaryRoomImageBase64(base64Image);
        }
    
        // Create a reservation DTO to pass data to the view
        ReservationDTO reservation = new ReservationDTO(roomType, price, maxGuests, checkinDate, checkoutDate, room.getAmenities(), room.getPrimaryRoomImageBase64(),roomId);
        model.addAttribute("roomCount", room.getRoomCount()); 
        model.addAttribute("reservation", reservation);
    
        return "new"; // Redirect to confirmation page
    }



    @PostMapping("/bookRoom")
    public String bookRoom(@RequestParam Long roomId,
                           @RequestParam String roomType,
                           @RequestParam String checkinDate,
                           @RequestParam String checkoutDate,
                           @RequestParam int maxGuests,
                           @RequestParam int personCount,
                           @RequestParam double price,
                           Model model) {
        // Retrieve room details
        Room room = roomService.getRoomById(roomId);
    
        // Create a new Booking object
        Booking booking = new Booking();
        booking.setRoom(room); // Assuming Room object is needed in the booking table
        booking.setRoomType(roomType);
        booking.setCheckInDate(java.sql.Date.valueOf(checkinDate));
        booking.setCheckOutDate(java.sql.Date.valueOf(checkoutDate));
        booking.setMaxGuests(maxGuests);
        booking.setPersonCount(personCount);
        booking.setTotalPrice(price * personCount);
        booking.setStatus("Confirmed");
    
        // Save the booking
        bookingService.saveBooking(booking);
    
        // Add booking confirmation details to the model
        model.addAttribute("booking", booking);
    
        return "booking"; // Redirect to a booking confirmation page
    }
       
}