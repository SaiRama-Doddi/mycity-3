package com.example.hotels.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hotel_amenities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelAmenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelAmenityId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;

    // Getters and Setters
}
