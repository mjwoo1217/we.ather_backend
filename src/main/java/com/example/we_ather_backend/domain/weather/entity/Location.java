package com.example.we_ather_backend.domain.weather.entity;

import com.example.we_ather_backend.common.BaseDateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "locations")
public class Location extends BaseDateEntity {

    @Id
    private Integer stn;
    private String name;
    private String enName;
}
