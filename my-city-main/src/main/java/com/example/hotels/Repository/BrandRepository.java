package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotels.Model.Brand;


public interface BrandRepository extends JpaRepository<Brand, Long> {
 
    // Brand findByBrandId(String brandId);

    // void save(BrandDto brandDto);
}
