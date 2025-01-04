package com.example.hotels.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tracks")
@Data
public class Tracks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackId;

    @Column(nullable = false, length = 150)
    private String title;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "albumId", nullable = false)
    private Albums album;

    @Column(nullable = true)
    private String duration;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    private byte[] musicFile;

   
}

