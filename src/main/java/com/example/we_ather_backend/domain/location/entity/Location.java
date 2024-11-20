package com.example.we_ather_backend.domain.location.entity;

import com.example.we_ather_backend.common.BaseDateEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
public class Location extends BaseDateEntity {

    @Id
    private Integer stn;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private String enName;
}
