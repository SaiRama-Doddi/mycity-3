package com.example.hotels.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "hotel_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hotelImageId;  // Unique identifier for the image

    @Lob  // Store the image as a large object (BLOB)
    private byte[] hotelImage;  // The binary data of the image

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    @ToString.Exclude
    private Hotel hotel;  // Foreign key linking the image to a specific hotel

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;  // Timestamp when the image was uploaded

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;  // Timestamp when the image was last updated

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = new java.util.Date();
        }
    }
    

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date(); // Update updatedAt whenever the entity is updated
    }

    public byte[] getHotelImage() {
        return this.hotelImage; // Ensure this returns the correct byte array
    }
    

    @Transient
    private String hotelImageBase64;
}
