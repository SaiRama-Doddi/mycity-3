package com.example.hotels.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotels.Model.City;
import com.example.hotels.Repository.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    public City getCityById(Long cityId) {
        return cityRepository.findById(cityId).orElse(null);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public void deleteCity(Long cityId) {
        cityRepository.deleteById(cityId);
    }
}

