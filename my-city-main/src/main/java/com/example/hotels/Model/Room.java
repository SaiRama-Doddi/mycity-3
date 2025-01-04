package com.example.hotels.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "room")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    @ToString.Exclude
    private Hotel hotel;

    private String roomType; // e.g., single, double, suite
    private Double pricePerNight;
    private Integer maxGuests;
    private Boolean isAvailable;
    private Integer roomCount; // Number of available rooms



public Integer getRoomCount() {
    return roomCount;
}

public void setRoomCount(Integer roomCount) {
    this.roomCount = roomCount;
}

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

  @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "room_amenities",
        joinColumns = @JoinColumn(name = "room_id"),
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities;


    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;


    @Transient
    private String primaryRoomImageBase64;

    public String getPrimaryRoomImageBase64() {
        return primaryRoomImageBase64;
    }

    public void setPrimaryRoomImageBase64(String primaryRoomImageBase64) {
        this.primaryRoomImageBase64 = primaryRoomImageBase64;
   } 

 

   public String getFormattedPrice() {
    if (pricePerNight != null) {
        return String.format("%.2f", pricePerNight); // Format price to two decimal places
    }
    return "N/A"; // Return "N/A" if price is null
}


}
