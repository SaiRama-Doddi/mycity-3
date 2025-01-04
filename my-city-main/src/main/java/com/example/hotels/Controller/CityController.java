package com.example.hotels.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.hotels.Model.City;
import com.example.hotels.Service.CityService;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/city")
    public City saveCity(City city) {
        return cityService.saveCity(city);
    }
    
    @PutMapping("/city")
    public City updateCity(City city) {
        return cityService.updateCity(city);
    }
    

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/city/{cityId}")
    public City getCityById(@PathVariable Long cityId) {
        return cityService.getCityById(cityId);
    }

    @DeleteMapping("/city/{cityId}")
    public String deleteCity(@PathVariable Long cityId) {
        cityService.deleteCity(cityId);
        return "City with id " + cityId + " is deleted";
    }
}
