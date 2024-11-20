package com.example.we_ather_backend.domain.location.service;


import com.example.we_ather_backend.domain.location.dto.LocationDTO;
import com.example.we_ather_backend.domain.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public List<LocationDTO> parseLocationDate(String response) {
        List<LocationDTO> locationList = new ArrayList<>();

        // 줄 단위로 데이터를 나눔
        String[] lines = response.split("\n");

        // 첫 번째 줄은 헤더이므로 무시하고 두 번째 줄부터 처리
        for (int i = 3; i < lines.length; i++) {
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
                // LocationDTO의 정적 팩토리 메서드를 활용
                LocationDTO dto = new LocationDTO(data);
                locationList.add(dto);
            } catch (NumberFormatException e) {
                // 숫자 변환이 실패하면 로그를 남기고 해당 줄을 건너뜀
                System.err.println("Location 데이터 변환 오류: " + e.getMessage());
            }
        }
        return locationList;
    }
}
