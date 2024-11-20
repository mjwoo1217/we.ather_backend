package com.example.we_ather_backend.domain.weather.dto;

import com.example.we_ather_backend.domain.weather.entity.Weather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {
    private String date;          // 관측일 (KST)
    private Integer stn;          // 관측지점
    private Double taAvg;         // 일 평균기온 (C)
    private Double taMax;         // 최고기온 (C)
    private Double taMin;         // 최저기온 (C)
    private Double caTot;         // 일 평균 전운량 (1/10)
    private Double rnDay;         // 강수량

public Weather of() {
    return new Weather(
            LocalDate.parse(this.date), // date 필드를 LocalDate로 변환
            this.stn,
            this.taAvg,
            this.taMax,
            this.taMin,
            this.caTot,
            this.rnDay
    );
}

}
