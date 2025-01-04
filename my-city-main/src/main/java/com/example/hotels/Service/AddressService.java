package com.example.hotels.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.Address;
import com.example.hotels.Repository.AddressRepository;

@Service
public class AddressService {
    
    @Autowired
    private AddressRepository addressRepository;

    public Address saveAddress(Address address){
        return addressRepository.save(address);
    }

    public Address updateAddress(Address address){
        return addressRepository.save(address);
    }

    public Address getAddressById(Long addressId){
        return addressRepository.findById(addressId).orElse(null);
    }

    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }

    public void deleteAddress(Long addressId){
        addressRepository.deleteById(addressId);
    }
}
