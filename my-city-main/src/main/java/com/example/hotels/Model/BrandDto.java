package com.example.hotels.Model;

import jakarta.validation.constraints.NotEmpty;

public class BrandDto {
    
    // @NotNull(message = "this field is required")
    private Long brandId;

    @NotEmpty(message = "this field is required")
    private String name;

    @NotEmpty(message = "this field is required")
    private String description;

    // private MultipartFile image;

    @NotEmpty(message = "this field is required")
    private String country;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // public MultipartFile getImage() {
    //     return image;
    // }

    // public void setImage(MultipartFile image) {
    //     this.image = image;
    // }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    
}
