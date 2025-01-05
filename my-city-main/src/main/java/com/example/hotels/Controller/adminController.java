package com.example.hotels.Controller;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotels.Model.Address;
import com.example.hotels.Model.City;
import com.example.hotels.Model.Hotel;
import com.example.hotels.Model.HotelImage;
import com.example.hotels.Model.Room;
import com.example.hotels.Model.RoomImage;
import com.example.hotels.Service.CityService;
import com.example.hotels.Service.HotelImageService;
import com.example.hotels.Service.HotelService;
import com.example.hotels.Service.RoomService;
import com.example.hotels.Service.RoomImageService;

@Controller
public class adminController {

    @Autowired
    private CityService cityService;
  
    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelImageService hotelImageService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomImageService roomImageService;

 


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/songs")
    public String songs() {
        return "songs";
    }

    @GetMapping("/hotels")
    public String ex(Model model) {
        List<Hotel> hotelsList1 = hotelService.getAllHotels();
        
        for (Hotel hotel : hotelsList1) {
            HotelImage primaryImage = hotelImageService.getPrimaryImageByHotelId(hotel.getHotelId());
            if (primaryImage != null && primaryImage.getHotelImage() != null) {
                // Convert image bytes to Base64
                String base64Image = Base64.getEncoder().encodeToString(primaryImage.getHotelImage());
                hotel.setPrimaryHotelImageBase64(base64Image); // This should now work
            }
            if (hotel.getAddress() == null) {
                Address defaultAddress = new Address();
                defaultAddress.setAddressLine2("Address not available");
                hotel.setAddress(defaultAddress);
            }
        }
    
        model.addAttribute("hotelsList1", hotelsList1);
        return "home";
    }



    @GetMapping("/sub-hotels")
    public String subHotels(@RequestParam("hotelId") Long hotelId, Model model) {
        // Fetch hotel by ID
        Hotel hotel = hotelService.getHotelById(hotelId);
        if (hotel == null) {
            throw new IllegalArgumentException("Invalid Hotel ID: " + hotelId);
        }


    
        // Fetch all related images for the hotel
        List<HotelImage> relatedImages = hotelImageService.getHotelImagesByHotelId(hotelId);

        List<Hotel> hotelsList1 = hotelService.getAllHotels();

        List<Hotel> hotelsList2 = hotelService.getAllHotels();

         List<Room> rooms = roomService.getRoomsByHotelId(hotelId);

         
         List<Room> roomList1 = roomService.getAllRooms();

         // Iterate through each room to fetch the primary image
         for (Room room : roomList1) {
             // Fetch the primary image for each room
             RoomImage primaryImage = roomImageService.getPrimaryImageByRoomId(room.getRoomId());
             
             // If a primary image exists, convert it to Base64 and set it
             if (primaryImage != null && primaryImage.getRoomImage() != null) {
                 String base64Image = Base64.getEncoder().encodeToString(primaryImage.getRoomImage());
                 room.setPrimaryRoomImageBase64(base64Image);  // Store the Base64 image in the Room object
             }
         }
    
        model.addAttribute("RoomList1 ",roomList1);
    

    
    
        // Encode each image to Base64
        for (HotelImage image : relatedImages) {
            if (image.getHotelImage() != null) {
                String base64Image = Base64.getEncoder().encodeToString(image.getHotelImage());
                image.setHotelImageBase64(base64Image); // Add this field dynamically
            }
        }

        for (Hotel hotels : hotelsList1) {
            HotelImage primaryImage = hotelImageService.getPrimaryImageByHotelId(hotels.getHotelId());
            if (primaryImage != null && primaryImage.getHotelImage() != null) {
                // Convert image bytes to Base64
                String base64Image = Base64.getEncoder().encodeToString(primaryImage.getHotelImage());
                hotels.setPrimaryHotelImageBase64(base64Image); // This should now work
            }
        }
        model.addAttribute("rooms", rooms);
        model.addAttribute("hotel", hotel);
        model.addAttribute("relatedImages", relatedImages);
        model.addAttribute("hotelsList1", hotelsList1); 
        model.addAttribute("hotelsList2", hotelsList2); 
        
        return "sub-hotels";
    }
    
    
    
      

    @GetMapping("/city-page")
    public String city(Model model) {
        // Empty city object for the form
        model.addAttribute("city", new City());
        // List of cities for the table
        model.addAttribute("cities", cityService.getAllCities());
        return "city"; // Display city page
    }
    
    @GetMapping("/showFormUpdatecity")
    public String showUpdate(@RequestParam("cityId") Long theId, Model model) {
        // Fetch the city by ID
        City theCity = cityService.getCityById(theId);
        // Add the city to the model
        model.addAttribute("city", theCity);
        // List of cities to display in the table
        model.addAttribute("cities", cityService.getAllCities());
        return "city"; // Return the same page for update
    }
    
    @PostMapping("/savecity")
    public String saveProperty(@ModelAttribute("city") City theCity) {
        // Save or update the city
        cityService.saveCity(theCity);
        return "redirect:/city-list"; // Redirect to the main city page after saving
    }
    

    @GetMapping("/city-list")
    public String cityList(Model model) {
        model.addAttribute("city", new City()); // Empty city object for the form
        model.addAttribute("cities", cityService.getAllCities()); // List of cities for the table
        return "city-list";
    }

    @GetMapping("/delete")
    public String deleteProperty(@RequestParam("cityId") Long theId){
        cityService.deleteCity(theId);
        return "redirect:/city-list";
    }




}
