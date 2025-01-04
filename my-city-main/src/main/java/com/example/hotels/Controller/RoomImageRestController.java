package com.example.hotels.Controller;

import com.example.hotels.Model.RoomImage;
import com.example.hotels.Service.RoomImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/room-images")
public class RoomImageRestController {

    @Autowired
    private RoomImageService roomImageService;

    // Save room image
    @PostMapping
    public ResponseEntity<RoomImage> uploadRoomImage(@RequestBody RoomImage roomImage) {
        RoomImage savedImage = roomImageService.saveRoomImage(roomImage);
        return ResponseEntity.ok(savedImage);
    }

    // Get images for a specific room
    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<RoomImage>> getImagesByRoom(@PathVariable Long roomId) {
        List<RoomImage> images = roomImageService.getRoomImages(roomId);
        return ResponseEntity.ok(images);
    }

    // Get image by imageId
    @GetMapping("/{imageId}")
    public ResponseEntity<RoomImage> getRoomImageById(@PathVariable Long imageId) {
        Optional<RoomImage> roomImage = roomImageService.getRoomImageById(imageId);
        return roomImage.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete room image
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteRoomImage(@PathVariable Long imageId) {
        roomImageService.deleteRoomImage(imageId);
        return ResponseEntity.noContent().build();
    }
}
