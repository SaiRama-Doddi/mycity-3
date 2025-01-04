package com.example.hotels.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Room;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

    @Query("SELECT r FROM Room r WHERE r.maxGuests >= :personCount AND r.roomId NOT IN (" +
       "SELECT b.room.roomId FROM Booking b WHERE " +
       "(:checkInDate BETWEEN b.checkInDate AND b.checkOutDate OR " +
       ":checkOutDate BETWEEN b.checkInDate AND b.checkOutDate OR " +
       "b.checkInDate BETWEEN :checkInDate AND :checkOutDate))")
List<Room> findAvailableRooms(@Param("personCount") int personCount,
                              @Param("checkInDate") LocalDate checkInDate,
                              @Param("checkOutDate") LocalDate checkOutDate);




                              @Query("SELECT r FROM Room r WHERE r.hotel.hotelId = :hotelId " +
                              "AND r.maxGuests >= :personCount " +
                              "AND NOT EXISTS (SELECT b FROM Booking b WHERE b.room = r " +
                              "AND b.checkInDate < :checkOut AND b.checkOutDate > :checkIn)")
                       List<Room> findAvailableRoomsByHotelAndGuests(
                           @Param("hotelId") Long hotelId,
                           @Param("personCount") int personCount,
                           @Param("checkIn") LocalDate checkIn,
                           @Param("checkOut") LocalDate checkOut
                       );


                   List<Room> findByHotelHotelId(Long hotelId);
                   List<Room> findAllByRoomId(Long roomId);



                   @Query("SELECT r FROM Room r WHERE r.maxGuests >= :personCount AND r.isAvailable = true")
                   List<Room> findAvailableRooms(@Param("personCount") int personCount, 
                                                 @Param("checkInDate") String checkInDate, 
                                                 @Param("checkOutDate") String checkOutDate);

}
