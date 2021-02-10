package com.example.weather.repository;

import com.example.weather.model.WeatherDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WeatherRepository extends JpaRepository<WeatherDao, UUID> {}
