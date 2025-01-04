package com.example.hotels.Model;

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
@Table(name = "room_amenities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomAmenities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomAmenityId;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;

    // Getters and Setters
}

