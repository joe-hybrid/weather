package com.example.weather.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class WeatherDto {

    private Integer id;

    private String main;

    private String description;

    private String icon;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}
