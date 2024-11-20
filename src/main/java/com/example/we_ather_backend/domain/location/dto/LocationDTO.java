package com.example.we_ather_backend.domain.location.dto;

import com.example.we_ather_backend.domain.location.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private Integer stn;          // 관측일 (KST)
    private String name ;          // 관측지점
    private String enName;         // 일 평균기온 (C)

    public LocationDTO(String[] data) {
        this.stn = Integer.valueOf(data[0].trim());;
        this.name = data.length > 10 ? data[10].trim() : null;
        this.enName = data.length > 11 ? data[11].trim() : null;
    }

    public Location of() {
        return new Location(this.stn, this.name, this.enName);
    }

}
