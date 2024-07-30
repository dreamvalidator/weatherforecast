package com.example.weatherforecast.repository;

import com.example.weatherforecast.model.Condition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConditionRepos extends JpaRepository<Condition,Integer> {
}
