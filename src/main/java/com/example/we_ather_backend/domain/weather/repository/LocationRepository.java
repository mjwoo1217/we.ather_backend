package com.example.we_ather_backend.domain.weather.repository;

import com.example.we_ather_backend.domain.weather.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
}
