package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepos extends JpaRepository<Location, Integer> {
}
