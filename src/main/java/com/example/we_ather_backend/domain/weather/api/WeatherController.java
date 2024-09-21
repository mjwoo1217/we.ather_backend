package com.example.we_ather_backend.domain.weather.api;

import com.example.we_ather_backend.domain.weather.dto.LocationDTO;
import com.example.we_ather_backend.domain.weather.dto.WeatherDTO;
import com.example.we_ather_backend.domain.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/weather")
    public Mono<List<WeatherDTO>> getWeather() {
        return weatherService.getWeather();
    }

    @GetMapping("/location")
    public Mono<List<LocationDTO>> getLocation() {
        return weatherService.getLocation();
    }
}
