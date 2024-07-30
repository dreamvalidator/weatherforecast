package com.example.weatherforecast.dto;

import com.example.weatherforecast.model.Current;
import com.example.weatherforecast.model.Location;
import lombok.Data;

@Data
public class WeatherResponse {

        private Location location;
        private Current current;

        // Getters and setters
    }
