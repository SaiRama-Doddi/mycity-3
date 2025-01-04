package com.example.hotels.Controller;

import com.example.hotels.Model.Room;
import com.example.hotels.Model.RoomImage;
import com.example.hotels.Service.RoomImageService;
import com.example.hotels.Service.RoomService;
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
public class RoomImageController {

    @Autowired
    private RoomImageService roomImageService;

    @Autowired
    private RoomService roomService;

    // Ping endpoint
    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "Server is up and running!";
    }

    // Display a specific image by ID
    @GetMapping("/room-images/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") Long imageId) throws IOException {
        RoomImage roomImage = roomImageService.getRoomImageById(imageId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + imageId));
        byte[] imageData = roomImage.getImageData();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
    }

    // View all room images
    @GetMapping("/room-images")
    public ModelAndView viewAllRoomImages() {
        ModelAndView modelAndView = new ModelAndView("room-images-list");
        List<RoomImage> imageList = roomImageService.getAllRoomImages();
        modelAndView.addObject("imageList", imageList);
        imageList.forEach(image -> System.out.println("Image ID: " + image.getImageId()));

        return modelAndView;
    }

    // Add  image - Get form
    @GetMapping("/room-images/add")
    public ModelAndView addImageForm(@RequestParam(value = "id", required = false) Long imageId) {
        ModelAndView modelAndView = new ModelAndView("room-images");
        
        if (imageId != null) {
            RoomImage roomImage = roomImageService.getRoomImageById(imageId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + imageId));
            modelAndView.addObject("roomImage", roomImage);
        } else {
            modelAndView.addObject("roomImage", new RoomImage());
        }
        
        modelAndView.addObject("rooms", roomService.getAllRooms());
        return modelAndView;
    }



     
    
    // Add - Post form
    @PostMapping("/room-images/save")
    public String saveImage(
            @RequestParam(value = "id", required = false) Long imageId,
            @RequestParam(value = "roomId", required = false) Long roomId,
            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {
    
        if (roomId == null) {
            throw new IllegalArgumentException("Room ID is required.");
        }
    
        Room room = roomService.getRoomById(roomId);
        if (room == null) {
            throw new IllegalArgumentException("Room with ID " + roomId + " does not exist.");
        }
    
        RoomImage roomImage;
        if (imageId != null) {
            roomImage = roomImageService.getRoomImageById(imageId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Image ID: " + imageId));
        } else {
            roomImage = new RoomImage();
        }
    
        roomImage.setRoom(room);
    
        if (image != null && !image.isEmpty()) {
            roomImage.setImageData(image.getBytes());
        }
    
        roomImageService.saveRoomImage(roomImage);
    
        return "redirect:/room-images";
    }
    
   

  // update image
  @GetMapping("/room-images/updating/{id}")
public ModelAndView showUpdateForm(@PathVariable Long id, Model model) {
    Optional<RoomImage> roomImage = roomImageService.getRoomImageById(id);

    if (roomImage.isPresent()) {
        RoomImage image = roomImage.get();

        if (image.getRoom() == null) {
            throw new IllegalStateException("Room is not set for this image.");
        }

        String base64Image = Base64.getEncoder().encodeToString(image.getImageData());
        model.addAttribute("base64Image", base64Image);
        model.addAttribute("rooms", roomService.getAllRooms());

        ModelAndView modelAndView = new ModelAndView("room-images-update");
        modelAndView.addObject("image", image);
        return modelAndView;
    } else {
        throw new IllegalArgumentException("Invalid Room Image ID: " + id);
    }
}


    
@PostMapping("/room-images/update")
public String updateRoomImage(
        @RequestParam("roomId") Long roomId,
        @RequestParam("imageId") Long imageId,
        @RequestParam(value = "image", required = false) MultipartFile image,
        Model model) {
    
    RoomImage roomImage = roomImageService.getRoomImageById(imageId)
            .orElseThrow(() -> new RuntimeException("Invalid image ID"));

    // Update room ID
    Room room = roomService.getRoomById(roomId);
    roomImage.setRoom(room);

    // Update image data only if a new file is uploaded
    if (image != null && !image.isEmpty()) {
        try {
            roomImage.setImageData(image.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to process uploaded image", e);
        }
    }

    roomImageService.saveRoomImage(roomImage); // Save the updated RoomImage object
    return "redirect:/room-images";
}
  







    // Delete an image by ID
    @GetMapping("/room-images/delete")
    public String deleteImage(@RequestParam("id") Long imageId) {
        roomImageService.deleteRoomImage(imageId);
        return "redirect:/room-images";
    }
}
