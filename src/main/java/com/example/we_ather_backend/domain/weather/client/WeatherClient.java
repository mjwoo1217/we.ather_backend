package com.example.we_ather_backend.domain.weather.client;

import com.example.we_ather_backend.domain.weather.dto.WeatherObservationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Value;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    @Value("${weather.api.key}")
    private String weather_apiKey;

    private final DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final WebClient clientConfig;

//    public Mono<WeatherObservationDTO> getWeather() {
//        return this.clientConfig.get().url()
//    }

}
