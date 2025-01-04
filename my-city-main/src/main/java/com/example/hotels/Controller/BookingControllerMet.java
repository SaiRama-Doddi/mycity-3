package com.example.hotels.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotels.Model.Booking;
import com.example.hotels.Model.Room;
import com.example.hotels.Model.RoomImage;
import com.example.hotels.Repository.BookingRepository;
import com.example.hotels.Service.BookingService;
import com.example.hotels.Service.RoomImageService;
import com.example.hotels.Service.RoomService;
import com.example.hotels.dto.ReservationDTO;

@Controller
@RequestMapping("/booking")
public class BookingControllerMet {

     private final BookingService bookingService;

    public BookingControllerMet(BookingService bookingService) {
        this.bookingService = bookingService;
    }


 @Autowired
    private BookingRepository bookingRepository;

        @Autowired
    private RoomService roomService;


    @Autowired
    private RoomImageService roomImageService;


    @GetMapping
    public String showBookingForm(Model model) {
        Booking booking = new Booking();
        // Initialize check-in and check-out dates with defaults or remove if unnecessary
        booking.setCheckInDate(new Date()); // Example: Set to the current date
        booking.setCheckOutDate(new Date()); // Example: Set to the current date
        model.addAttribute("reservation", booking);
        return "booking"; // Ensure this maps to booking.html in your templates folder
    }

    
   /*  @PostMapping("/reserve")
    public String reserveBooking(@ModelAttribute Booking booking, Model model) {
        System.out.println("Room ID: " + booking.getRoomId()); // Debugging roomId value
        Room room = bookingService.getRoomById(booking.getRoomId()); // Fetch the Room object
        booking.setRoom(room); // Associate the Room object with the Booking
        bookingService.saveBooking(booking); // Save the booking
        model.addAttribute("reservation", booking);
        return "booking"; // Ensure this maps to booking.html
    }
     */

@PostMapping("/reserve")
public String confirmReservation(@RequestParam Long roomId, 
                                 @RequestParam String roomType,
                                 @RequestParam double price,
                                 @RequestParam int maxGuests,
                                 @RequestParam String checkinDate,
                                 @RequestParam String checkoutDate,
                                 Model model) {
    // Convert Strings to Date
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date checkinDateParsed = null;
    Date checkoutDateParsed = null;
    
    try {
        checkinDateParsed = sdf.parse(checkinDate);
        checkoutDateParsed = sdf.parse(checkoutDate);
    } catch (ParseException e) {
        e.printStackTrace();  // Handle parsing exception
    }

    // Retrieve room details dynamically
    Room room = roomService.getRoomById(roomId); 

    // Set room image if available
    RoomImage primaryImage = roomImageService.getPrimaryImageByRoomId(roomId);
    if (primaryImage != null && primaryImage.getRoomImage() != null) {
        String base64Image = Base64.getEncoder().encodeToString(primaryImage.getRoomImage());
        room.setPrimaryRoomImageBase64(base64Image);
    }

    // Create a reservation DTO with parsed dates
    ReservationDTO reservation = new ReservationDTO(roomType, price, maxGuests, checkinDate, checkoutDate, room.getAmenities(), room.getPrimaryRoomImageBase64(), roomId);


        // Debugging to check if reservation object is populated correctly
        System.out.println("Room ID: " + reservation.getRoomId());
        System.out.println("Room Type: " + reservation.getRoomType());
        System.out.println("Check-in Date: " + checkinDateParsed);
        System.out.println("Check-out Date: " + checkoutDateParsed);
        System.out.println("Price: " + reservation.getPrice());
    
    
    // Add model attributes, passing the Date objects
    model.addAttribute("checkinDateParsed", checkinDateParsed);
    model.addAttribute("checkoutDateParsed", checkoutDateParsed);
    model.addAttribute("roomCount", room.getRoomCount()); 
    model.addAttribute("booking", reservation);

    return "booking";
}


@GetMapping("/book")
public String book(){
    return "booking";
}

}