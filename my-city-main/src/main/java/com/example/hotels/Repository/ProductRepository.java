package com.example.hotels.Repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    @Query("SELECT p FROM Product p JOIN FETCH p.brand JOIN FETCH p.category")
    List<Product> findAllWithBrandAndCategory(Sort sort);

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);


    @Query("SELECT p FROM Product p WHERE p.brand.brandId = :brandId")
    List<Product> findByBrandId(@Param("brandId") Long brandId);
}
