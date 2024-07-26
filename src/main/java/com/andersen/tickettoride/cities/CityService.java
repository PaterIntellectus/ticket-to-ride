package com.andersen.tickettoride.cities;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    private final CityRepository cityRepository;


    CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public City findByName(String name) {
        final var city = cityRepository.findByName(name);

        if (city == null) {
            throw new EntityNotFoundException("Could not find city with name: " + name);
        }

        return city;
    }
}
