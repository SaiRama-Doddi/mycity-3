package com.example.hotels.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Shopping_Images;


@Repository
public interface Shopping_ImagesRepository extends JpaRepository<Shopping_Images, Long> {

    List<Shopping_Images> findByProduct_ProductId(Long productId);
}
