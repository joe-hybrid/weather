package com.example.weather.controller;

import com.example.weather.api.ErrorDto;
import com.example.weather.api.WeatherApi;
import com.example.weather.api.WeatherResponse;
import com.example.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WeatherController implements WeatherApi {

    private final WeatherService weatherService;

    private final WeatherMapper weatherMapper;

    @Override
    public WeatherResponse getWeatherForCity(String city) {
        return weatherMapper.daoToResponse(
                weatherService.getWeatherForCity(city)
        );
    }

    /**
     * Handler for the exceptions which should return an Internal Server Error to the client.
     *
     * @param req which caused the ba request
     * @param ex the thrown exception
     * @return ErrorInfoDTO which holds the information about the error.
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDto handleInternalServerError(HttpServletRequest req, Exception ex) {
        log.error("Handling an 500 Internal Server Error exception", ex);
        return new ErrorDto(
                ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                req.getRequestURI()
        );
    }

}
