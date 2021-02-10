package com.example.weather.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/v1/weather")
public interface WeatherApi {

    /**
     * Get weather details for the given city.
     *
     * @param city name of the city to fetch weather data for
     * @return list of {@link WeatherResponse}
     */
    @Operation(
            summary = "Get OpenWeather data for the given city",
            operationId = "getWeatherForCity",
            parameters = {
                    @Parameter(
                            in = ParameterIn.QUERY,
                            name = "city",
                            description = "name of the city to fetch weather data for",
                            example = "Amsterdam"
                    )
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Weather details for the given city"),
                    @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
                    @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
                    @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorDto.class))),
                    @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorDto.class)))
            }
    )
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    WeatherResponse getWeatherForCity(@RequestParam(value = "city") String city);

}
