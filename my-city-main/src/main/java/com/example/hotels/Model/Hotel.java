package com.example.hotels.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/* import org.hibernate.annotations.Cascade;
import org.hibernate.annotation.CascadeType; */


@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"address", "rooms", "reviews", "amenities", "hotelAmenities", "city"})
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelId;

    private String name;

    private String description;
    private Double rating;
    private String contactInfo;

    

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false) // Define the foreign key column
    private City city;
  
    // Add a new column for the map source URL or coordinates
    @Column(name = "map_source",length = 500)
    private String mapSource;

    // Getters and Setters for the map source
    public String getMapSource() {
        return mapSource;
    }

    public void setMapSource(String mapSource) {
        this.mapSource = mapSource;
    }



    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;
    

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;
    
     // Helper method to add a room
     public void addRoom(Room room) {
        rooms.add(room);
        room.setHotel(this);
    }

    // Helper method to remove a room
    public void removeRoom(Room room) {
        rooms.remove(room);
        room.setHotel(null);
    }

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

   
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "hotel_amenities", 
        joinColumns = @JoinColumn(name = "hotel_id"), 
        inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private List<Amenity> amenities; 


     @OneToMany(mappedBy = "hotel")
    private List<HotelAmenities> hotelAmenities;
    
   
    public List<HotelAmenities> getHotelAmenities() {
        return hotelAmenities;
    }
    
    public void setHotelAmenities(List<HotelAmenities> hotelAmenities) {
        this.hotelAmenities = hotelAmenities;
    } 

    // Getters and Setters
    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
        updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }


    @Transient
    private String primaryHotelImageBase64;

    public String getPrimaryHotelImageBase64() {
        return primaryHotelImageBase64;
    }

    public void setPrimaryHotelImageBase64(String primaryHotelImageBase64) {
        this.primaryHotelImageBase64 = primaryHotelImageBase64;
    }

    
    
}
