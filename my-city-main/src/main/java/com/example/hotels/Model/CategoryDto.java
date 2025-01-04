package com.example.hotels.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryDto {
    
    private Long categoryId;

    @NotEmpty(message = "this field is required")
    private String name;

    @NotEmpty(message = "this field is required")
    private String description;
}
