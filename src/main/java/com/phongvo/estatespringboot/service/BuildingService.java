package com.phongvo.estatespringboot.service;

import com.phongvo.estatespringboot.builder.BuildingBuilder;
import com.phongvo.estatespringboot.dto.BuildingDto;
import com.phongvo.estatespringboot.dto.BuildingResponse;
import com.phongvo.estatespringboot.dto.BuildingTypeResponse;
import com.phongvo.estatespringboot.dto.Ids;
import com.phongvo.estatespringboot.entity.Building;
import com.phongvo.estatespringboot.entity.RentArea;
import com.phongvo.estatespringboot.entity.User;
import com.phongvo.estatespringboot.enums.BuildingTypeEnum;
import com.phongvo.estatespringboot.exceptions.BuildingNotFoundException;
import com.phongvo.estatespringboot.mapper.BuildingMapper;
import com.phongvo.estatespringboot.paging.PageRequest;
import com.phongvo.estatespringboot.repository.BuildingRepository;
import com.phongvo.estatespringboot.repository.RentAreaRepository;
import com.phongvo.estatespringboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BuildingService {

    private final RentAreaRepository rentAreaRepository;
    private final UserRepository userRepository;
    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;


    public BuildingResponse save(BuildingDto buildingDto) {
        Building building = buildingMapper.dtoToEntity(buildingDto);
        if(buildingDto.getId() != null){
            Building old = buildingRepository.getOne(buildingDto.getId());
            building.setCreatedDate(old.getCreatedDate());
            building.setCreatedBy(old.getCreatedBy());
            building.setRentAreas(old.getRentAreas());
            building.setUserAssigments(old.getUserAssigments());
            building.setUserOfBuildingPriorities(old.getUserOfBuildingPriorities());
            if(rentAreaRepository.existsByBuilding_Id(buildingDto.getId())){
                rentAreaRepository.deleteByBuilding_Id(buildingDto.getId());
            }
        }
        if(buildingDto.getRentArea() != null && StringUtils.isNotBlank(buildingDto.getRentArea())){
            building = saveRentAreaByBuilding(building, buildingDto);
        }
        return buildingMapper.EntityToResponse(buildingRepository.save(building));
    }

    public List<BuildingResponse> findAll(BuildingDto buildingDto){
        List<Building> buildings = buildingRepository.findAll(convertBuildingBuilder(buildingDto),
                new PageRequest(buildingDto.getOffSet(), buildingDto.getLimit()));
        return buildingMapper.listEntityToListResponse(buildings);
    }

    private BuildingBuilder convertBuildingBuilder(BuildingDto buildingDto){
        return BuildingBuilder.builder()
                .name(buildingDto.getName())
                .areaFrom(buildingDto.getAreaFrom())
                .areaTo(buildingDto.getAreaTo())
                .costFrom(buildingDto.getCostFrom())
                .costTo(buildingDto.getCostTo())
                .direction(buildingDto.getDirection())
                .district(buildingDto.getDistrict())
                .floorArea(buildingDto.getFloorarea())
                .level(buildingDto.getLevel())
                .ownerName(buildingDto.getOwnerName())
                .ownerPhone(buildingDto.getOwnerPhone())
                .staffId(buildingDto.getStaffId())
                .numberOfBasement(buildingDto.getNumberOfBasement())
                .street(buildingDto.getStreet())
                .ward(buildingDto.getWard())
                .build();
    }

    private Building saveRentAreaByBuilding(Building building, BuildingDto buildingDto) {
        List<RentArea> rentAreas = new ArrayList<>();
        for (String i : buildingDto.getRentArea().split(",")) {
            RentArea rentArea = new RentArea();
            rentArea.setValue(Integer.parseInt(i));
            rentArea.setBuilding(building);
            rentAreas.add(rentArea);
        }
        building.setRentAreas(rentAreas);
        return building;
    }

    public void delete(Ids ids) {
        for(Long i : ids.getIds()){
            Building building = buildingRepository.getOne(i);
            rentAreaRepository.deleteByBuilding_Id(building.getId());
            for(User user : building.getUserAssigments()){
                user.getBuildings().remove(building);
            }
            for(User user : building.getUserOfBuildingPriorities()){
                user.getBuildingPriorities().remove(building);
            }
            buildingRepository.delete(building);
        }
    }

    public BuildingResponse findBuildingById(Long id) {
        Building building = buildingRepository.findById(id)
                .orElseThrow(()->new BuildingNotFoundException("Not Found Building By "+ id));
        return buildingMapper.EntityToResponse(building);
    }

    public List<BuildingResponse> getPriorBuildingsByUserId(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new UsernameNotFoundException("NOT FOUND USER BY " + id));
        return buildingMapper.listEntityToListResponse(user.getBuildingPriorities());
    }

    public List<BuildingTypeResponse> getAllBuildingTypes() {
        List<BuildingTypeResponse> buildingTypeResponses = new ArrayList<>();
        EnumSet.allOf(BuildingTypeEnum.class)
                .forEach(item -> {
                    BuildingTypeResponse typeResponse = new BuildingTypeResponse();
                    typeResponse.setName(item.getBuildingTypeName());
                    typeResponse.setCode(item.toString());
                    buildingTypeResponses.add(typeResponse);
                });
        return buildingTypeResponses;
    }
}
