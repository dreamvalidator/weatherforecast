package com.example.weatherforecast.service;

import com.example.weatherforecast.dto.WeatherResponse;
import com.example.weatherforecast.model.Condition;
import com.example.weatherforecast.model.Current;
import com.example.weatherforecast.model.Location;
import com.example.weatherforecast.repository.ConditionRepos;
import com.example.weatherforecast.repository.CurrentRepos;
import com.example.weatherforecast.repository.LocationRepos;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.url}")
    private String url;

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private LocationRepos locationRepos;

    @Autowired
    private CurrentRepos currentRepos;

    @Autowired
    private ConditionRepos conditionRepos;

    private volatile String location ;
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    public WeatherResponse getWeather(String q, String key) {
        String apiUrl = url + "?q=" + q + "&key=" + key;
        ResponseEntity<WeatherResponse> responseEntity = restTemplate.getForEntity(apiUrl, WeatherResponse.class);

        WeatherResponse weatherBody = responseEntity.getBody();
        if (weatherBody != null) {
            saveWeather(weatherBody);
        }
        return weatherBody;
    }

    private void saveWeather(WeatherResponse weather) {
        Location mapLocation = modelMapper.map(weather.getLocation(), Location.class);
        Current mapCurrent = modelMapper.map(weather.getCurrent(), Current.class);

        if (weather.getCurrent().getCondition() != null) {
            Condition condition = modelMapper.map(weather.getCurrent().getCondition(), Condition.class);

            mapCurrent.setCondition(condition);

            currentRepos.save(mapCurrent);
        }


        locationRepos.save(mapLocation);
 //
    }

    @Scheduled(fixedRate = 60000)//for every one minute update
    public void scheduledWeatherUpdate() {
        logger.info("Fetching weather data for scheduled update.");
        getWeather(location, apiKey);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getApiKey() {
        return apiKey;
    }
}
