package com.example.hotels.Model;

import java.util.Base64;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "images")
@Data
public class Shopping_Images{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Lob
    @Column(name="image", nullable=false, columnDefinition = "LONGBLOB")
    private byte[] images;

    @Transient
    private String base64Image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public String getBase64Image() {
        return Base64.getEncoder().encodeToString(this.images);
    }
}
