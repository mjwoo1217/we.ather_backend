package com.example.we_ather_backend.domain.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private Integer stn;          // 관측일 (KST)
    private String name ;          // 관측지점
    private String enName;         // 일 평균기온 (C)

}
