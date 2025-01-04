package com.example.hotels.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Tracks;


@Repository
public interface TrackRepository extends JpaRepository<Tracks, Long> {
    
}
