package com.example.weather.client;

import com.example.weather.client.dto.OpenWeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client to access OpenWeather API
 */
@FeignClient(
        name = "open-weather-api-client",
        url = "${open-weather-api.host}",
        primary = false
)
public interface OpenWeatherClient {

    @GetMapping
    OpenWeatherResponse getWeather(@RequestParam String q, @RequestParam String appid, @RequestParam String units);

}
