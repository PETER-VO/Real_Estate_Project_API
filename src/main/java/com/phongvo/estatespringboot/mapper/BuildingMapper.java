package com.phongvo.estatespringboot.mapper;

import com.phongvo.estatespringboot.dto.BuildingDto;
import com.phongvo.estatespringboot.dto.BuildingResponse;
import com.phongvo.estatespringboot.entity.Building;
import com.phongvo.estatespringboot.repository.RentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RentAreaRepository rentAreaRepository;

    public Building dtoToEntity(BuildingDto buildingRequest){
        return modelMapper.map(buildingRequest,Building.class);
    }

    public BuildingResponse EntityToResponse(Building building){
        BuildingResponse buildingResponse = modelMapper.map(building, BuildingResponse.class);
        buildingResponse.setRentArea(building.getRentAreas().stream().map(rentArea -> String.valueOf(rentArea.getValue())).collect(Collectors.joining(",")));
        buildingResponse.setInChargeStaffs(building.getUserAssigments().stream().map(user -> String.valueOf(user.getId())).collect(Collectors.joining(",")));
        return buildingResponse;
    }
    public List<BuildingResponse> listEntityToListResponse(List<Building> buildings){
        List<BuildingResponse> buildingResponses = new ArrayList<>();
        for(Building building : buildings){
            BuildingResponse buildingResponse = EntityToResponse(building);
            buildingResponses.add(buildingResponse);
        }
        return buildingResponses;
    }
}
