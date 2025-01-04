package com.example.hotels.Controller;

import com.example.hotels.Model.Hotel;
import com.example.hotels.Model.HotelImage;
import com.example.hotels.Service.HotelImageService;
import com.example.hotels.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
public class HotelImageController {

    @Autowired
    private HotelImageService hotelImageService;

    @Autowired
    private HotelService hotelService;

    // Display a specific image by ID
    @GetMapping("/hotel-images/display")
    public ResponseEntity<byte[]> displayHotelImage(@RequestParam("id") Long imageId) throws IOException {
        HotelImage hotelImage = hotelImageService.getHotelImageById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + imageId));
        byte[] imageData = hotelImage.getHotelImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }

    // View all hotel images
    @GetMapping("/hotel-images")
    public ModelAndView viewAllHotelImages() {
        ModelAndView modelAndView = new ModelAndView("hotel-images-list");
        List<HotelImage> imageList = hotelImageService.getAllHotelImages();
        modelAndView.addObject("imageList", imageList);
        imageList.forEach(image -> System.out.println("Hotel Image ID: " + image.getHotelImageId()));
        imageList.forEach(image -> System.out.println("Image Created At: " + image.getCreatedAt()));

        return modelAndView;
    }

    // Add image - Get form
    @GetMapping("/hotel-images/add")
    public ModelAndView addHotelImageForm(@RequestParam(value = "id", required = false) Long imageId) {
        ModelAndView modelAndView = new ModelAndView("hotel-images");
        
        if (imageId != null) {
            HotelImage hotelImage = hotelImageService.getHotelImageById(imageId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + imageId));
            modelAndView.addObject("hotelImage", hotelImage);
        } else {
            modelAndView.addObject("hotelImage", new HotelImage());
        }
        
        modelAndView.addObject("hotels", hotelService.getAllHotels());
        return modelAndView;
    }

    // Add - Post form
    @PostMapping("/hotel-images/save")
    public String saveHotelImage(
            @RequestParam(value = "id", required = false) Long imageId,
            @RequestParam(value = "hotelId", required = false) Long hotelId,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

        if (hotelId == null) {
            throw new IllegalArgumentException("Hotel ID is required.");
        }

        Hotel hotel = hotelService.getHotelById(hotelId);
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel with ID " + hotelId + " does not exist.");
        }

        HotelImage hotelImage;
        if (imageId != null) {
            hotelImage = hotelImageService.getHotelImageById(imageId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + imageId));
        } else {
            hotelImage = new HotelImage();
        }

        hotelImage.setHotel(hotel);

        if (image != null && !image.isEmpty()) {
            hotelImage.setHotelImage(image.getBytes());
        }

        hotelImageService.saveHotelImage(hotelImage);

        return "redirect:/hotel-images";
    }


    @GetMapping("/hotel-images/updating/{id}")
    public ModelAndView showUpdateForm(@PathVariable Long id, Model model) {
     Optional<HotelImage> hotelImage=hotelImageService.getHotelImageById(id);
      if(hotelImage.isPresent()){
        HotelImage image=hotelImage.get();

        if(image.getHotel()==null){
            throw new IllegalStateException("hotel is not set for this image. ");
            }
            String base64Image=Base64.getEncoder().encodeToString(image.getHotelImage());
            model.addAttribute("base64Image", base64Image);
            model.addAttribute("hotels", hotelService.getAllHotels());

           
        ModelAndView modelAndView = new ModelAndView("hotel-images-update");
        modelAndView.addObject("image", image);
        return modelAndView; 
        }
        else {
            throw new IllegalArgumentException("Invalid hotel Image ID: " + id);
        }
    
        
    }
    
    @PostMapping("/hotel-images/update")
    public String updateHotelImage(
            @RequestParam("hotelId") Long hotelId,
            @RequestParam("hotelImageId") Long hotelImageId,
            @RequestParam(value = "image", required = false) MultipartFile image) {
    
        HotelImage hotelImage = hotelImageService.getHotelImageById(hotelImageId)
            .orElseThrow(() -> new RuntimeException("Invalid Hotel Image ID"));
    
        Hotel hotel = hotelService.getHotelById(hotelId);
        hotelImage.setHotel(hotel);
    
        if (image != null && !image.isEmpty()) {
            try {
                hotelImage.setHotelImage(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to process uploaded image", e);
            }
        }
    
        hotelImageService.saveHotelImage(hotelImage);
        return "redirect:/hotel-images";
    }


    // Delete an image by ID
    @GetMapping("/hotel-images/delete")
    public String deleteHotelImage(@RequestParam("id") Long hotelImageId) {
        hotelImageService.deleteHotelImage(hotelImageId);
        return "redirect:/hotel-images";
    }
}
