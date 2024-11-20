package com.example.we_ather_backend.domain.location.dto;

import com.example.we_ather_backend.domain.location.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private Integer stn;          // 관측일 (KST)
    private String name ;          // 관측지점
    private String enName;         // 일 평균기온 (C)

    private LocationDTO(String[] data) {
        this.stn = Integer.valueOf(data[0].trim());;
        this.name = data.length > 10 ? data[10].trim() : null;
        this.enName = data.length > 11 ? data[11].trim() : null;
    }

    public static List<LocationDTO> parseLocationDate(String response) {
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
                // 생성자를 통해 DTO 객체 생성
                LocationDTO dto = new LocationDTO(data);

                locationList.add(dto);
            } catch (NumberFormatException e) {
                // 숫자 변환이 실패하면 로그를 남기고 해당 줄을 건너뜀
                System.err.println("Location 데이터 변환 오류: " + e.getMessage());
            }
        }
        return locationList;
    }

    public Location of() {
        return new Location(
                this.stn,
                this.name,
                this.enName
        );
    }

}
