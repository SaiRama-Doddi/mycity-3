package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
    
}
