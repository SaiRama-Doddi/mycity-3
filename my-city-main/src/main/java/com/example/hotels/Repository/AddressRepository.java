package com.example.hotels.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hotels.Model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
     // Custom query to fetch Address along with its associated Hotel
    @Query("SELECT a FROM Address a LEFT JOIN FETCH a.hotel")
    List<Address> findAllAddressesWithHotel();
    
}
