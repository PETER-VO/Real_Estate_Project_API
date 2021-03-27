package com.phongvo.estatespringboot.controller;


import com.phongvo.estatespringboot.dto.BuildingDto;
import com.phongvo.estatespringboot.dto.BuildingResponse;
import com.phongvo.estatespringboot.dto.BuildingTypeResponse;
import com.phongvo.estatespringboot.dto.Ids;
import com.phongvo.estatespringboot.service.BuildingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping(value = "/api/building")
@AllArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @PostMapping("/new")
    public ResponseEntity<Object> createBuilding(@RequestBody BuildingDto buildingDto){
        return new ResponseEntity<>(buildingService.save(buildingDto), CREATED);
    }

    @PostMapping("/all")
    public List<BuildingResponse> getBuilding(@RequestBody  BuildingDto buildingDto){
        return buildingService.findAll(buildingDto);
    }

    @PutMapping
    public ResponseEntity<Object> editBuilding(@RequestBody BuildingDto buildingDto){
        return new ResponseEntity<>(buildingService.save(buildingDto), OK);
    }

    @GetMapping("/{buildingId}")
    public ResponseEntity<Object> getBuildingById(@PathVariable("buildingId") Long id){
        return new ResponseEntity<>(buildingService.findBuildingById(id), OK);
    }

    @GetMapping("/priority/{userId}")
    public List<BuildingResponse> getPriorBuildingsByUserId(@PathVariable("userId") Long id){
        return buildingService.getPriorBuildingsByUserId(id);
    }

    @GetMapping("/type")
    public List<BuildingTypeResponse> getAllBuildingTypes(){
        return buildingService.getAllBuildingTypes();
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteBuilding(@RequestBody Ids ids){
        buildingService.delete(ids);
        return new ResponseEntity<>(OK);
    }
}
