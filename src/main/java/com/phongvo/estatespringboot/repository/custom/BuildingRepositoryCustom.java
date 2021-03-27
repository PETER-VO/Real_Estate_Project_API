package com.phongvo.estatespringboot.repository.custom;

import com.phongvo.estatespringboot.builder.BuildingBuilder;
import com.phongvo.estatespringboot.entity.Building;
import com.phongvo.estatespringboot.paging.PageRequest;

import java.util.List;


public interface BuildingRepositoryCustom {
    List<Building> findAll(BuildingBuilder builder, PageRequest pageRequest);
}
