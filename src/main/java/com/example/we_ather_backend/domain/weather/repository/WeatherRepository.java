package com.example.we_ather_backend.domain.weather.repository;

import com.example.we_ather_backend.domain.weather.entity.Location;
import com.example.we_ather_backend.domain.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

}
