package com.example.hotels.Model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductDto {
    
    private Long productId;

    @NotEmpty(message = "this field is required")
    private String name;

    @NotEmpty(message = "this field is required")
    private String specifications;   
    
    @NotEmpty(message = "this field is required")
    private String highlights;

    @NotEmpty(message = "this field is required")
    private String display;

    @NotNull(message = "This field is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "This field is required")
    @Positive(message = "Original price must be greater than zero")
    private Double originalPrice;

    @NotNull(message = "This field is required")
    @Positive(message = "This rating must be greater than zero")
    private Double rating;

    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Stock cannot be negative")
    private Integer stock;

    @NotNull(message = "This field is required")
    @PositiveOrZero(message = "Discount cannot be negative")
    private Double discount;

    private MultipartFile imageFile;

    @NotNull(message = "this field is required")
    private Long brandId;

    @NotNull(message = "this field is required")
    private Long categoryId;
}
