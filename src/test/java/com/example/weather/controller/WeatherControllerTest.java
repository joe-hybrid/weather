package com.example.weather.controller;

import com.example.weather.BaseMvcIntegrationTest;
import com.example.weather.api.WeatherResponse;
import com.example.weather.client.OpenWeatherClient;
import com.example.weather.client.dto.MainDto;
import com.example.weather.client.dto.OpenWeatherResponse;
import com.example.weather.client.dto.SysDto;
import com.example.weather.model.WeatherDao;
import com.example.weather.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class WeatherControllerTest extends BaseMvcIntegrationTest {

    private static final String WEATHER_PATH = "/api/v1/weather";

    @Autowired
    private WeatherRepository weatherRepository;

    @MockBean
    private OpenWeatherClient openWeatherClient;

    @Test
    void getWeatherForCity() throws Exception {
        String city = "Amsterdam";
        String country = "NL";
        Double temperature = 5.67;

        when(openWeatherClient.getWeather(any(), any(), any())).thenReturn(
                generateOpenWeatherResponse(city, country, temperature)
        );

        ResultActions resultActions = mockMvc.perform(get(WEATHER_PATH)
                .param("city", city))
                .andExpect(status().isOk());

        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        WeatherResponse response = objectMapper.readValue(contentAsString, WeatherResponse.class);

        Optional<WeatherDao> weatherDao = weatherRepository.findOne(Example.of(WeatherDao.builder()
                .city(city)
                .country(country)
                .temperature(temperature)
                .build()));

        assertTrue(weatherDao.isPresent());
        assertEquals(weatherDao.get().getCity(), city);
        assertEquals(weatherDao.get().getCountry(), country);
        assertEquals(weatherDao.get().getTemperature(), temperature);

        assertNotNull(response);
        assertEquals(response.getId(), weatherDao.get().getId());
        assertEquals(response.getCity(), city);
        assertEquals(response.getCountry(), country);
        assertEquals(response.getTemperature(), temperature);
    }

    @Test
    void getWeatherForCityInternalServerError() throws Exception {
        mockMvc.perform(get(WEATHER_PATH))
                .andExpect(status().isInternalServerError());
    }

    private OpenWeatherResponse generateOpenWeatherResponse(String city, String country, Double temperature) {
        return OpenWeatherResponse.builder()
                .name(city)
                .main(MainDto.builder().temp(temperature).build())
                .sys(SysDto.builder().country(country).build())
                .build();
    }

}