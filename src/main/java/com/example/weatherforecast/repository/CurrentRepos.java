package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.Current;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentRepos extends JpaRepository< Current,Integer> {
}
