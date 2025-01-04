package com.example.hotels.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.hotels.Model.Address;
import com.example.hotels.Model.Hotel;
import com.example.hotels.Service.AddressService;
import com.example.hotels.Service.HotelService;

@Controller
@RequestMapping("/address")
public class addressControllerMeth {

    @Autowired
    private AddressService addressService;


    @Autowired
    private HotelService hotelService;

    @GetMapping("/add")
    public String showAddAddressForm(Model model) {
        model.addAttribute("address", new Address());
        List<Hotel> theList = hotelService.getAllHotels();  // Get all hotels
        model.addAttribute("hotels", theList);  // Add the list of hotels to the model
       
        return "add-address";  // Pass the list of hotels to the form
    }
    

@PostMapping("/save")
public String saveAddress(@ModelAttribute("address") Address address,Model model) {

    if (address.getHotel() != null && address.getHotel().getHotelId() != null) {
        Hotel hotel = hotelService.getHotelById(address.getHotel().getHotelId());
        address.setHotel(hotel); // Set the fetched Hotel entity to Address
    }
    addressService.saveAddress(address);
    model.addAttribute("hotels", hotelService.getAllHotels());
    return "redirect:/addresses"; // Redirect to the list of addresses
}



 // Show Address List Page
    @GetMapping("/list")
    public String showAddressList(Model model) {
        model.addAttribute("addresses", addressService.getAllAddresses());
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "address-list";
    }

    // Handle Delete Action
    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return "redirect:/address/list";
    }

    // Handle Edit Action
    @GetMapping("/edit/{id}")
    public String editAddress(@PathVariable Long id, Model model) {
        Address address = addressService.getAddressById(id);
        model.addAttribute("address", address);
        model.addAttribute("hotels", hotelService.getAllHotels());
        return "add-address";
    }
    
}