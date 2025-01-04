package com.example.hotels.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.CartItem;
import com.example.hotels.Model.Product;



@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
    List<CartItem> findByProduct(Product product);
}
