package com.example.hotels.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomDTO {
    private Long roomId;
    private String roomType;
    private Double pricePerNight;
    private Integer maxGuests;
    private Boolean isAvailable;
    private String primaryRoomImageBase64;
    private String hotelName;
  
}