package com.example.hotels.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "brand")
@Data
public class Brand {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long brandId;

    private String name;
    private String description;
    // private String image;
    private String country;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Product> products;
}
