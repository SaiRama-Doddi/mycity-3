package com.example.hotels.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "amenity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amenityId;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "amenities")
    private List<Hotel> hotels;

    @ManyToMany(mappedBy = "amenities")
    private List<Room> rooms;

    // Getters and Setters
}

