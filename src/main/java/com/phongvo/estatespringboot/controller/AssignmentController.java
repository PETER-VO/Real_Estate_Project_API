package com.phongvo.estatespringboot.controller;


import com.phongvo.estatespringboot.dto.AssigmentRequest;
import com.phongvo.estatespringboot.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @PostMapping("/building")
    public ResponseEntity<String> saveAssignmentForStaffByBuildingId(@RequestBody AssigmentRequest assigmentRequest){
        assignmentService.saveForBuilding(assigmentRequest);
        return new ResponseEntity<>("Assigned Staff For Building Successfully",OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<String> saveAdssignmentForStaffCustomerI(@RequestBody AssigmentRequest assigmentRequest){
        assignmentService.saveForCustomer(assigmentRequest);
        return new ResponseEntity<>("Assigned Staff For Customer Successfully",OK);
    }

}
