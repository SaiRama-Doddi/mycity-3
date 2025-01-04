package com.example.hotels.Repository;

import com.example.hotels.Model.HotelImage;
import com.example.hotels.Model.RoomImage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {
    // You can add custom query methods here if necessary
    List<RoomImage> findByRoom_RoomId(Long roomId);

    RoomImage findFirstByRoom_RoomIdOrderByCreatedAtAsc(Long roomId);

}
