package com.example.hotels.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hotels.Model.Amenity;
import com.example.hotels.Service.AmenityService;

import jakarta.transaction.Transactional;

import java.util.List;

@Controller
@RequestMapping("/amenities")
public class AmenityControllerMethod {

    @Autowired
    private AmenityService amenityService;

    // Display the form to add a new amenity
    @GetMapping("/add")
    public String showAddAmenityForm(Model model) {
        model.addAttribute("amenity", new Amenity());
        return "add-amenity";
    }

    @GetMapping("/showFormUpdate/{id}")
    public String showUpdateForm(@PathVariable("id") Long amenityId, Model model) {
        Amenity existingAmenity = amenityService.getAmenityById(amenityId);
        model.addAttribute("amenity", existingAmenity);
        return "add-amenity"; // Navigates to the same form used for adding amenities
    }
    
    // Handle form submission for both add and update operations
    @Transactional
    @PostMapping("/save")
    public String saveOrUpdateAmenity(@ModelAttribute Amenity amenity) {
        amenityService.saveAmenity(amenity);
        return "redirect:/amenities/list";
    }
    
    // Display a list of all amenities
    @GetMapping("/list")
    public String listAmenities(Model model) {
        List<Amenity> amenities = amenityService.getAllAmenities();
        model.addAttribute("amenities", amenities);
        return "list-amenities";
    }

    // Delete an amenity
    @Transactional
    @GetMapping("/delete/{id}")
    public String deleteAmenity(@PathVariable("id") Long id) {
        amenityService.deleteAmenity(id);
        return "redirect:/amenities/list";
    }

    
}

