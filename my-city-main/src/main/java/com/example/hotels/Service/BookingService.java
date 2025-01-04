package com.example.hotels.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.Booking;
import com.example.hotels.Model.Room;
import com.example.hotels.Repository.BookingRepository;
import com.example.hotels.Repository.RoomRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    public BookingService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Find available rooms based on person count and date range
    public List<Room> findAvailableRooms(int personCount, String checkInDate, String checkOutDate) {
        return roomRepository.findAvailableRooms(personCount, checkInDate, checkOutDate);
    }

    // Save a new booking
    public Booking saveBooking(Booking booking) {
        validateBooking(booking);
        return bookingRepository.save(booking);
    }

    // Update an existing booking
    public Booking updateBooking(Booking booking) {
        validateBooking(booking);
        return bookingRepository.save(booking);
    }

    // Get a booking by its ID
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found with ID: " + bookingId));
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Delete a booking by its ID
    public void deleteBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new IllegalArgumentException("Booking not found with ID: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }

    // Check if a room is available for booking
    public boolean isRoomAvailable(Long roomId, String checkInDate, String checkOutDate) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + roomId));
        return room.getIsAvailable();
    }

    // Get a room by its ID
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("Room not found with ID: " + roomId));
    }

    // Private helper method to validate booking details
    private void validateBooking(Booking booking) {
        if (booking.getRoom() == null) {
            throw new IllegalArgumentException("Booking must be associated with a room.");
        }
       /*  if (booking.getUser() == null) {
            throw new IllegalArgumentException("Booking must be associated with a user.");
        } */
        if (booking.getCheckInDate() == null || booking.getCheckOutDate() == null) {
            throw new IllegalArgumentException("Check-in and check-out dates are required.");
        }
        if (booking.getCheckInDate().after(booking.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-in date cannot be after check-out date.");
        }
        if (booking.getPersonCount() > booking.getRoom().getMaxGuests()) {
            throw new IllegalArgumentException("Person count exceeds the room's maximum capacity.");
        }
    }
}