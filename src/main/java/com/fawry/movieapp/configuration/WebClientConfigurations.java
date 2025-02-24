package com.fawry.movieapp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Configuration
public class WebClientConfigurations {

    @Value("${movie.api.url}")
    private String apiUrl;

    @Value("${movie.api.key}")
    private String apiKey;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("apiKey", apiKey))
                .build();
    }
}
