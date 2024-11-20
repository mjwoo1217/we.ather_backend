package com.example.we_ather_backend.domain.location.client;

import com.example.we_ather_backend.domain.location.dto.LocationDTO;
import com.example.we_ather_backend.domain.location.repository.LocationRepository;
import com.example.we_ather_backend.domain.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocationClient {

    private final WebClient weatherInfoClient;

    private LocationRepository locationRepository;
    private LocationService locationService;

    @Value("${weather.api.key}")
    private String weather_apiKey;

    public Mono<List<LocationDTO>> getLocation() {
        return weatherInfoClient.get().uri(uriBuilder -> uriBuilder
                        .path("api/typ01/url/stn_inf.php")
                        .queryParam("authKey", weather_apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(byte[].class)
                .map(bytes -> new String(bytes, Charset.forName("EUC-KR"))) // EUC-KR로 변환
                .map(locationService::parseLocationDate); // 데이터를 파싱하여 List<WeatherDTO>로 변환
    }

}
