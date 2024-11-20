package com.example.we_ather_backend.domain.location.repository;

import com.example.we_ather_backend.domain.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
