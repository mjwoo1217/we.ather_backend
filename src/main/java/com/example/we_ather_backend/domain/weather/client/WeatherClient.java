package com.example.we_ather_backend.domain.weather.client;

import com.example.we_ather_backend.domain.weather.dto.WeatherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private final WebClient weatherInfoClient;

    @Value("${weather.api.key}")
    private String weather_apiKey;

    // weather webclient
    public Mono<List<WeatherDTO>> getWeather() {
        return weatherInfoClient.get().uri(uriBuilder -> uriBuilder
                        .path("api/typ01/url/kma_sfcdd3.php")
                        .queryParam("tm1", "201512110100")
                        .queryParam("tm2","201512140000")
                        .queryParam("authKey", weather_apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(byte[].class)
                .map(bytes -> new String(bytes, Charset.forName("EUC-KR"))) // EUC-KR로 변환
                .map(this::parseDate) // 데이터를 파싱하여 List<WeatherDTO>로 변환
         ; // 데이터 저장
    }

    // weather 정보 변환
    private List<WeatherDTO> parseDate(String response) {
        List<WeatherDTO> weatherList = new ArrayList<>();

        // 줄 단위로 데이터를 나눔
        String[] lines = response.split("\n");

        // 첫 번째 줄은 헤더이므로 무시하고 두 번째 줄부터 처리
        for (int i = 1; i < lines.length; i++) {
            String dataLine = lines[i].trim();

            // 줄이 빈 경우 건너뜀
            if (dataLine.isEmpty()) {
                continue;
            }

            // 공백을 기준으로 데이터를 분리
            String[] data = dataLine.split("\\s+");

            // 배열의 길이가 2 이상이어야 함 (예: data[0], data[1] 접근을 위해)
            if (data.length < 2) {
                continue;  // 데이터가 부족하면 건너뜀
            }

            try {
                // 각 필드를 DTO에 매핑
                WeatherDTO dto = new WeatherDTO();
                dto.setDate(data[0].trim());
                dto.setStn(Integer.valueOf(data[1].trim()));

                // 데이터 길이에 맞게 안전하게 값들을 추출
                if (data.length > 10) dto.setTaAvg(Double.parseDouble(data[10].trim()));
                if (data.length > 11) dto.setTaMax(Double.parseDouble(data[11].trim()));
                if (data.length > 13) dto.setTaMin(Double.parseDouble(data[13].trim()));
                if (data.length > 31) dto.setCaTot(Double.parseDouble(data[31].trim()));
                if (data.length > 38) dto.setRnDay(Double.parseDouble(data[38].trim()));

                weatherList.add(dto);
            } catch (NumberFormatException e) {
                // 숫자 변환이 실패하면 로그를 남기고 해당 줄을 건너뜀
                System.err.println("데이터 변환 오류: " + e.getMessage());
            }
        }
        return weatherList;
    }
}
