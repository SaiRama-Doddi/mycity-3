package com.example.hotels.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
@Data
public class Albums {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long albumId;
    
    @Column(nullable = false, length = 150)
    private String title;
    
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
    
    private LocalDate releaseDate;
    
    @Lob
    @Column(name = "cover_image", columnDefinition = "LONGBLOB")
    private byte[] coverImage;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tracks> tracks=new ArrayList<>();
    
    // Getters and Setters
}

