package com.example.weather.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysDto {

    private Integer type;

    private Integer id;

    private Double message;

    private String country;

    private Integer sunrise;

    private Integer sunset;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}
