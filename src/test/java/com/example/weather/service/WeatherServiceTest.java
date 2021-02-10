package com.example.weather.service;

import com.example.weather.BaseDbIntegrationTest;
import com.example.weather.client.OpenWeatherClient;
import com.example.weather.client.dto.MainDto;
import com.example.weather.client.dto.OpenWeatherResponse;
import com.example.weather.client.dto.SysDto;
import com.example.weather.model.WeatherDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class WeatherServiceTest extends BaseDbIntegrationTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private OpenWeatherClient openWeatherClient;

    @Test
    void getWeatherForCity() {
        String city = "Amsterdam";
        String country = "NL";
        Double temperature = 5.67;

        when(openWeatherClient.getWeather(any(), any(), any())).thenReturn(
                generateOpenWeatherResponse(city, country, temperature)
        );

        WeatherDao weatherDao = weatherService.getWeatherForCity(city);

        assertNotNull(weatherDao);
        assertEquals(weatherDao.getCity(), city);
        assertEquals(weatherDao.getCountry(), country);
        assertEquals(weatherDao.getTemperature(), temperature);
    }

    private OpenWeatherResponse generateOpenWeatherResponse(String city, String country, Double temperature) {
        return OpenWeatherResponse.builder()
                .name(city)
                .main(MainDto.builder().temp(temperature).build())
                .sys(SysDto.builder().country(country).build())
                .build();
    }

}
