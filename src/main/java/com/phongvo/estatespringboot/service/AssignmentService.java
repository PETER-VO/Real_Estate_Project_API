package com.phongvo.estatespringboot.service;

import com.phongvo.estatespringboot.dto.AssigmentRequest;
import com.phongvo.estatespringboot.entity.Building;
import com.phongvo.estatespringboot.entity.Customer;
import com.phongvo.estatespringboot.entity.User;
import com.phongvo.estatespringboot.exceptions.BuildingNotFoundException;
import com.phongvo.estatespringboot.repository.BuildingRepository;
import com.phongvo.estatespringboot.repository.CustomerRepository;
import com.phongvo.estatespringboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AssignmentService {

    private final BuildingRepository buildingRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public void saveForBuilding(AssigmentRequest assigmentRequest) {
        Building building = buildingRepository.findById(assigmentRequest.getBuildingId())
                .orElseThrow(()->new BuildingNotFoundException("NOT FOUND BUILDING ID " + assigmentRequest.getBuildingId()));
        List<User> staffs = new ArrayList<>();
        for(Long id : assigmentRequest.getStaffIds()){
            User user = userRepository.findById(id)
                    .orElseThrow(()->new UsernameNotFoundException("NOT FOUND USER ID " + id));
            staffs.add(user);
        }
        building.setUserAssigments(staffs);
        buildingRepository.save(building);
    }

    public void saveForCustomer(AssigmentRequest assigmentRequest) {
        Customer customer = customerRepository.findById(assigmentRequest.getCustomerId())
                .orElseThrow(()->new BuildingNotFoundException("NOT FOUND BUILDING ID " + assigmentRequest.getBuildingId()));
        List<User> staffs = new ArrayList<>();
        for(Long id : assigmentRequest.getStaffIds()){
            User user = userRepository.findById(id)
                    .orElseThrow(()->new UsernameNotFoundException("NOT FOUND USER ID " + id));
            staffs.add(user);
        }
        customer.setUsers(staffs);
        customerRepository.save(customer);
    }



}
