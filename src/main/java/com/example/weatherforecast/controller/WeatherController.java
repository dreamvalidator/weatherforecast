package com.example.weatherforecast.controller;

import com.example.weatherforecast.dto.WeatherResponse;
import com.example.weatherforecast.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WeatherController {

    @Autowired
    private   WeatherService weatherService;

    @GetMapping("/weather")
    public ResponseEntity<?> searchWeather(@RequestParam String q, @RequestParam String key) {
        WeatherResponse weather = weatherService.getWeather(q, key);
        System.out.println(weather);
        return ResponseEntity.ok(weather);
    }

}
