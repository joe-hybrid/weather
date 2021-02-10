package com.example.weather.config;

import com.example.weather.api.ErrorDto;
import com.example.weather.config.properties.OpenApiProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MimeTypeUtils;

import java.util.Map;

/**
 * Configuration for OpenAPI 3.x.
 */
@EnableConfigurationProperties(OpenApiProperties.class)
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI openAPI() {
        final Schema<ErrorDto> errorSchema = new Schema<>();
        errorSchema.example(new ErrorDto("timestamp", 500, "error", "message", "traceId"));
        errorSchema.$ref(ErrorDto.class.getSimpleName());

        return new OpenAPI()
                .components(new Components()
                        .responses(Map.of(
                                "400", new ApiResponse().description("Bad Request e.g. missing User-Agent")
                                        .content(new Content().addMediaType(MimeTypeUtils.APPLICATION_JSON_VALUE, new MediaType().schema(errorSchema))),
                                "403", new ApiResponse().description("Forbidden e.g. token invalid/expired or the action is not allowed with the authenticated person/user")
                                        .content(new Content().addMediaType(MimeTypeUtils.APPLICATION_JSON_VALUE, new MediaType().schema(errorSchema))),
                                "404", new ApiResponse().description("Not found")
                                        .content(new Content().addMediaType(MimeTypeUtils.APPLICATION_JSON_VALUE, new MediaType().schema(errorSchema))),
                                "500", new ApiResponse().description("Internal server error")
                                        .content(new Content().addMediaType(MimeTypeUtils.APPLICATION_JSON_VALUE, new MediaType().schema(errorSchema)))))
                )
                .info(createInfo());
    }

    public Info createInfo() {
        return new Info()
                .title("Example - Weather Service")
                .version("1.0")
                .contact(new Contact()
                        .name("Weather Team")
                        .url("https://example.com/weather-api")
                        .email("someone@example.com"));
    }

}
