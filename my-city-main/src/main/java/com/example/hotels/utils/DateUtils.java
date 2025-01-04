package com.example.hotels.utils;

import java.time.LocalDate;

public class DateUtils {
    
    public static boolean isValidDateRange(LocalDate checkIn, LocalDate checkOut) {
        LocalDate today = LocalDate.now();
        
        // Check if dates are not null
        if (checkIn == null || checkOut == null) {
            return false;
        }
        
        // Check if check-in is not in the past
        if (checkIn.isBefore(today)) {
            return false;
        }
        
        // Check if check-out is after check-in
        if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
            return false;
        }
        
        return true;
    }
}