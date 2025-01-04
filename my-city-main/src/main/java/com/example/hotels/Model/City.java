package com.example.hotels.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "city")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    
    private String name;
    private String state;
    private String country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Hotel> hotels;

    // Getters and Setters
    
     // Override toString() to return only the city name
     @Override
     public String toString() {
         return name; // This ensures the dropdown displays the city name
     }
}

