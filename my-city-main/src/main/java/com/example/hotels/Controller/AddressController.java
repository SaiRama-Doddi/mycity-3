package com.example.hotels.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotels.Model.Address;
import com.example.hotels.Service.AddressService;

@RestController
public class AddressController {
    
    @Autowired
    private AddressService addressService;

    @PostMapping("/address")
    public Address saveAddress(@RequestBody Address address){
        return addressService.saveAddress(address);
    }

    @PutMapping("/address")
    public Address updateAddress(@RequestBody Address address){
        return addressService.updateAddress(address);
    }

    @GetMapping("/address/{addressId}")
    public Address getAddressById(@PathVariable Long addressId){
        return addressService.getAddressById(addressId);
    }

    @GetMapping("/addresses")
    public List<Address> getAllAddresses(){
        return addressService.getAllAddresses();
    }

    @DeleteMapping("/address/{addressId}")
    public String deleteAddress(@PathVariable Long addressId){
        addressService.deleteAddress(addressId);
        return "Address deleted successfully";
    }
}
