package com.example.hotels.dto;

import java.util.List;

import com.example.hotels.Model.Amenity;

public class ReservationDTO {
    private String roomType;
    private double price;
    private int maxGuests;
    private String checkinDate;
    private String checkoutDate;
    private List<Amenity> amenities;
    private String primaryRoomImageBase64;
    public Long roomId;
    

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    // Constructor
    public ReservationDTO(String roomType, double price, int maxGuests, String checkinDate, String checkoutDate, List<Amenity> amenities, String primaryRoomImageBase64,Long roomId) {
        this.roomType = roomType;
        this.price = price;
        this.maxGuests = maxGuests;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.amenities = amenities;
        this.primaryRoomImageBase64 = primaryRoomImageBase64;
        this.roomId=roomId;
    }

    // Getters and setters (optional, if needed)
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public List<Amenity> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }

    public String getPrimaryRoomImageBase64() {
        return primaryRoomImageBase64;
    }

    public void setPrimaryRoomImageBase64(String primaryRoomImageBase64) {
        this.primaryRoomImageBase64 = primaryRoomImageBase64;
    }
    
}