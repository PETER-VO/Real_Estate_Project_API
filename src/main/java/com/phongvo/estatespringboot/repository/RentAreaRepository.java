package com.phongvo.estatespringboot.repository;

import com.phongvo.estatespringboot.entity.RentArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentAreaRepository extends JpaRepository<RentArea,Long> {
    void deleteByBuilding_Id(Long buildingId);
    boolean existsByBuilding_Id(Long buildingId);
}
