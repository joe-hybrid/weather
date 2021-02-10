package com.example.weather.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherResponse {

    private CoordDto coord;

    private List<WeatherDto> weather = null;

    private String base;

    private MainDto main;

    private Integer visibility;

    private WindDto wind;

    private CloudsDto clouds;

    private Integer dt;

    private SysDto sys;

    private Integer id;

    private String name;

    private Integer cod;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}
