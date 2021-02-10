package com.example.weather.service;

import com.example.weather.client.OpenWeatherClient;
import com.example.weather.client.dto.OpenWeatherResponse;
import com.example.weather.model.WeatherDao;
import com.example.weather.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class WeatherService {

    private final OpenWeatherClient openWeatherClient;

    private final WeatherRepository weatherRepository;

    @Value("${open-weather-api.appid}")
    private String openWeatherApiAppId;

    public WeatherDao getWeatherForCity(String city) {
        log.debug("Calling OpenWeather API for city {}", city);
        OpenWeatherResponse response = openWeatherClient.getWeather(city, openWeatherApiAppId, "metric");
        log.debug("Saving weather info to database for city {}", city);
        return weatherRepository.save(WeatherDao.builder()
                .city(response.getName())
                .country(response.getSys().getCountry())
                .temperature(response.getMain().getTemp())
                .build());
    }

}
