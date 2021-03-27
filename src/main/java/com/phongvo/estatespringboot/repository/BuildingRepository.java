package com.phongvo.estatespringboot.repository;

import com.phongvo.estatespringboot.entity.Building;
import com.phongvo.estatespringboot.repository.custom.BuildingRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long>, BuildingRepositoryCustom {

}
