package com.example.hotels.Model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

   /*  @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
     @ToString.Exclude
    private User user;  */

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

  

    @Temporal(TemporalType.DATE)
    private Date checkInDate;

    @Temporal(TemporalType.DATE)
    private Date checkOutDate;

    private String status; // e.g., confirmed, canceled, completed
    private Double totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    /* @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private Payment payment; */

     // New field for person count
     private Integer personCount;


     private int maxGuests;

    // Getters and Setters

    private String roomType;
   

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


    @Transient // This field is only for form handling
    private Long roomId;

    // Getters and Setters
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
 

}