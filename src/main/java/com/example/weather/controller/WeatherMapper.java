package com.example.weather.controller;

import com.example.weather.api.WeatherResponse;
import com.example.weather.model.WeatherDao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    WeatherResponse daoToResponse(WeatherDao dao);

}
