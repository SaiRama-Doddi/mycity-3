package com.example.hotels.Controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hotels.Model.Address;
import com.example.hotels.Model.Amenity;
import com.example.hotels.Model.City;
import com.example.hotels.Model.Hotel;
import com.example.hotels.Model.HotelAmenities;
import com.example.hotels.Model.HotelImage;
import com.example.hotels.Model.Room;
import com.example.hotels.Repository.RoomRepository;
import com.example.hotels.Service.AddressService;
import com.example.hotels.Service.AmenityService;
import com.example.hotels.Service.CityService;
import com.example.hotels.Service.HotelAmenitiesService;
import com.example.hotels.Service.HotelImageService;
import com.example.hotels.Service.HotelService;
import com.example.hotels.Service.RoomService;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CityService cityService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private HotelImageService hotelImageService;


     @Autowired
    private RoomService roomService;


    @Autowired
    private AmenityService amenityService;
/* 
    @Autowired
    private HotelAmenities hotelAmenities; */

    @Autowired
    private HotelAmenitiesService hotelAmenitiesService; 



 @GetMapping("/hotels/{id}")
    public String showHotelDetails(@PathVariable Long id, Model model) {
        // Fetch hotel by ID
        Hotel hotel = hotelService.getHotelById(id);
        
        // Fetch related images
        List<HotelImage> relatedImages =hotelImageService.getHotelImagesByHotelId(id);

        // Add attributes to the model
        model.addAttribute("hotel", hotel);
        model.addAttribute("relatedImages", relatedImages != null ? relatedImages : new ArrayList<>());

        // Return the Thymeleaf template
        return "sub-hotels";
    }
    

    // Show Add Hotel Form
    @GetMapping("/hoteladd")
    public String showAddHotelForm(Model model) {
        List<Amenity> amenities = amenityService.getAllAmenities(); // Fetch amenities from the database
        model.addAttribute("amenities", amenities);
        model.addAttribute("hotel", new Hotel());
         List<City> cities = cityService.getAllCities();  // Fetch cities from the database
         model.addAttribute("cities", cities); 
        List<Address> addresses=addressService.getAllAddresses();
        model.addAttribute("address",addresses); 
    /*   model.addAttribute("rooms", roomService.getAllRooms());  */
        return "hotels";
    }


    // Show Update Hotel Form
    @GetMapping("/hotel/showFormUpdate")
    public String showUpdate(@RequestParam("hotelId") Long hotelId, Model model) {
        Hotel theHotel = hotelService.getHotelById(hotelId); // Fetch the hotel by ID
        
        model.addAttribute("hotel", theHotel);
        model.addAttribute("cities", cityService.getAllCities()); 
         model.addAttribute("address", addressService.getAllAddresses()); 
        /* model.addAttribute("rooms", roomService.getAllRooms());   */
        model.addAttribute("amenities", amenityService.getAllAmenities()); 
 

        return "hotels"; // Return the correct view name
    }


    @PostMapping("/save")
public String saveHotel(@ModelAttribute Hotel hotel, @RequestParam Long addressId, @RequestParam List<Long> amenities) {
    Address address = addressService.getAddressById(addressId);
    hotel.setAddress(address);
    
    // Check for existing hotel to prevent duplicate entries
    if (hotel.getHotelId() != null) {
        Hotel existingHotel = hotelService.getHotelById(hotel.getHotelId());
        if (existingHotel != null) {
            hotel.setCreatedAt(existingHotel.getCreatedAt());
        }
    }

    List<Amenity> selectedAmenities = amenityService.getAmenitiesByIds(amenities);
    hotel.setAmenities(selectedAmenities);

    hotelService.saveHotel(hotel); 
    return "redirect:/list"; 
}


    // Display List of Hotels
     @GetMapping("/list")
    public String listHotels(Model model) {
        List<Hotel> hotels = hotelService.getAllHotels();
        model.addAttribute("amenities", amenityService.getAllAmenities()); 
        model.addAttribute("hotels", hotels);
        model.addAttribute("hotelamenities", hotelAmenitiesService.getAllHotelAmenities());
        model.addAttribute("address",addressService.getAllAddresses());
      
        return "hotels-list";
    }

    // Delete Hotel
    @GetMapping("/hotel/delete/{id}")
    public String deleteHotel(@PathVariable("id") Long id) {
        Hotel hotel = hotelService.getHotelById(id);
    
        // Clear relationships
        if (hotel.getRooms() != null) {
            hotel.getRooms().forEach(room -> room.setHotel(null));
            hotel.getRooms().clear();
        }
        if (hotel.getReviews() != null) {
            hotel.getReviews().forEach(review -> review.setHotel(null));
            hotel.getReviews().clear();
        }
    
        // Delete the hotel
        hotelService.deleteHotel(id);
        return "redirect:/list";
     }
    

    @GetMapping("/search-hotels")
public String searchHotels(@RequestParam("query") String query, Model model) {
    List<Hotel> filteredHotels = hotelService.searchHotels(query);
    model.addAttribute("hotelsList1", filteredHotels);
    List<Hotel> hotelsList1 = hotelService.getAllHotels();

     for (Hotel hotel : hotelsList1) {
            HotelImage primaryImage = hotelImageService.getPrimaryImageByHotelId(hotel.getHotelId());
            if (primaryImage != null && primaryImage.getHotelImage() != null) {
                // Convert image bytes to Base64
                String base64Image = Base64.getEncoder().encodeToString(primaryImage.getHotelImage());
                hotel.setPrimaryHotelImageBase64(base64Image); // This should now work
            }
        }
    return "home"; // Your Thymeleaf template name
}




}
