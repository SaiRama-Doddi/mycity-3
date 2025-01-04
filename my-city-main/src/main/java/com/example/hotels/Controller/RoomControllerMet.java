package com.example.hotels.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hotels.Model.City;
import com.example.hotels.Model.Hotel;
import com.example.hotels.Model.Room;
import com.example.hotels.Model.RoomImage;
import com.example.hotels.Repository.RoomRepository;
import com.example.hotels.Service.AmenityService;
import com.example.hotels.Service.CityService;
import com.example.hotels.Service.HotelService;
import com.example.hotels.Service.RoomAmenitiesService;
import com.example.hotels.Service.RoomImageService;
import com.example.hotels.Service.RoomService;
import com.example.hotels.dto.ApiResponse;
import com.example.hotels.dto.RoomDTO;
import com.example.hotels.utils.DateUtils;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RoomControllerMet {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelService hotelService;

 
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AmenityService amenityService;

    @Autowired
    private CityService cityService;


        @Autowired
    private RoomImageService roomImageService;


    @GetMapping("/room/new")
    public String addRoom(Model model) {
        model.addAttribute("room", new Room()); // Provide a blank Room object
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("amenities", amenityService.getAllAmenities());
        model.addAttribute("cities", cityService.getAllCities()); 
        return "add-room";
    }

    // Save new room or update existing room
  @PostMapping("/room")
public String saveRoom(@ModelAttribute Room room, @RequestParam Long hotelId,@RequestParam(required = false) Long cityId) {
    // Retrieve the hotel by ID and set it to the room
    Hotel hotel = hotelService.getHotelById(hotelId);
    room.setHotel(hotel);

    if (cityId != null) {
        City city = cityService.getCityById(cityId);
        room.setCity(city);  // Set the city if it's provided
    }
    
    roomService.saveRoom(room);
    return "redirect:/rooms";
}


    // Update existing room
    @GetMapping("/room/edit/{roomId}")
    public String editRoom(@PathVariable Long roomId, Model model) {
        System.out.println("Room ID: " + roomId);
        Room room = roomService.getRoomById(roomId);
     
      
        model.addAttribute("room", room);
        model.addAttribute("hotels", hotelService.getAllHotels());
        model.addAttribute("amenities", amenityService.getAllAmenities());
        model.addAttribute("cities", cityService.getAllCities());
        return "add-room";
    }
    
    
    

    // List all rooms
    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);
        model.addAttribute("amenities", amenityService.getAllAmenities());
        return "room-list";
    }

    // Delete room by ID
    @GetMapping("/room/delete/{roomId}")
    public String deleteRoom(@PathVariable Long roomId,Model model) {
        roomService.deleteRoom(roomId);
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "redirect:/rooms";
    }


    @GetMapping("/filter")
    public String getRoomsByHotelId(@RequestParam("hotelId") Long hotelId, Model model) {
        List<Room> rooms = roomService.getRoomsByHotelId(hotelId);
        model.addAttribute("rooms", rooms);
        return "reserve";  // This should be the name of the HTML template where rooms are displayed.
    }



    @GetMapping("/room-details/{roomId}")
public String getRoomDetails(@PathVariable Long roomId, Model model) {
    Room room = roomService.getRoomById(roomId); // Fetch the room details
    model.addAttribute("room", room); // Add the room object to the model
    return "reserve"; // Return the Thymeleaf template name
}



@GetMapping("/reserve")
public String reserveRoom(@RequestParam("roomId") Long roomId,
                          @RequestParam("roomType") String roomType,
                          @RequestParam("price") Double price,
                          @RequestParam("maxGuests") int maxGuests,
                          Model model) {
   
    Room room = roomService.getRoomById(roomId);
    room.setRoomType(roomType);
    room.setPricePerNight(price);
    room.setMaxGuests(maxGuests);
    
    model.addAttribute("room", room);
    return "reserve"; 
}



@PostMapping("/reserve")
public String reserveRooms(@RequestParam Long roomId,
                          @RequestParam String roomType,
                          @RequestParam Double price,
                          @RequestParam int maxGuests,
                          Model model) {
    
    Room room = roomService.getRoomById(roomId);

    room.setRoomType(roomType);
    room.setPricePerNight(price);
    room.setMaxGuests(maxGuests);


    RoomImage primaryImage = roomImageService.getPrimaryImageByRoomId(roomId);
    if (primaryImage != null && primaryImage.getRoomImage() != null) {
        String base64Image = Base64.getEncoder().encodeToString(primaryImage.getRoomImage());
        room.setPrimaryRoomImageBase64(base64Image);
    }

  


    model.addAttribute("room", room);

    return "reserve"; 
}



 /*    @GetMapping("/reserve")
public String reserveRoom(@RequestParam("roomId") Long roomId, Model model) {
  
    Room room = roomService.getRoomById(roomId);

 
    model.addAttribute("room", room);

    return "reserve"; 
}  */

    
  /*     @GetMapping("/rooms/availability")
    public ResponseEntity<?> getAvailableRooms(
            @RequestParam(required = true) String personCount,
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate) {

        try {
            // Validate person count
            if (personCount == null || personCount.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Person count is required", null));
            }

            int personCountInt;
            try {
                personCountInt = Integer.parseInt(personCount);
                if (personCountInt <= 0) {
                    return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, "Person count must be greater than 0", null));
                }
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Invalid person count format", null));
            }

            // Validate dates
            if (checkInDate == null || checkOutDate == null) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Both check-in and check-out dates are required", null));
            }

            if (!DateUtils.isValidDateRange(checkInDate, checkOutDate)) {
                return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, "Invalid date range. Check-in date must be before check-out date and not in the past", null));
            }

            // Get available rooms
            List<RoomDTO> availableRooms = roomService.findAvailableRooms(personCountInt, checkInDate, checkOutDate);
            
            return ResponseEntity.ok(new ApiResponse(true, "Rooms retrieved successfully", availableRooms));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body(new ApiResponse(false, "An error occurred while processing your request", null));
        }
  
    }
 */

  @GetMapping("/rooms/availability")
 public ResponseEntity<?> getAvailableRooms(
     @RequestParam Long hotelId,
     @RequestParam int personCount,
     @RequestParam String checkInDate,
     @RequestParam String checkOutDate
 ) {
     try {
         List<Room> rooms = roomService.findAvailableRoomsByHotelAndGuests(hotelId, personCount, checkInDate, checkOutDate);
         return ResponseEntity.ok(rooms);
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
             .body(Collections.singletonMap("error", "Error fetching available rooms: " + e.getMessage()));
     }
 }

 

 @PostMapping("/roomfilter")
public String filterRooms(@RequestParam(required = false) Long roomId, @RequestParam(required = false) Integer personCount) {
    if (roomId == null) {
        return "error"; // Handle error if roomId is not provided
    }

    if (personCount == null) {
        personCount = 1; // Set a default value if personCount is not provided
    }

    Room room = roomService.getRoomById(roomId);
    if (room != null && room.getIsAvailable()) {
        // Proceed with the reservation logic or filtering
        return "redirect:/sub-hotels"; // Or a page that shows results
    } else {
        return "error"; // Handle case where room is not available
    }
}
   

}
