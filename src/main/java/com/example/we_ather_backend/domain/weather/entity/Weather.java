package com.example.we_ather_backend.domain.weather.entity;

import com.example.we_ather_backend.common.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Weather extends BaseEntity {
    private LocalDate date;          // 관측일 (KST) - "20151211"
    private Integer stn;          // 관측지점
    private Double taAvg;         // 일 평균기온 (C) - 6.4
    private Double taMax;         // 최고기온 (C) - 12.2
    private Double taMin;         // 최저기온 (C) - 3.3
    private Double caTot;         // 일 평균 전운량 (1/10) - 2.0
    private Double rnDay;         // 강수량
}
