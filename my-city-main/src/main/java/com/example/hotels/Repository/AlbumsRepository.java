package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Albums;


@Repository
public interface AlbumsRepository extends JpaRepository<Albums, Long> {
    
}
