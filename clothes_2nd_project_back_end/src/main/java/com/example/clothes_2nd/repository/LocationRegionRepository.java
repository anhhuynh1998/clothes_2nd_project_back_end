package com.example.clothes_2nd.repository;

import com.example.clothes_2nd.model.LocationRegion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRegionRepository extends JpaRepository<LocationRegion, Long> {
    LocationRegion getLocationRegionByUserInfoId(Long id);

    void deleteByUserInfoId(Long id);
}
