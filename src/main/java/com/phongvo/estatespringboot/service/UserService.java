package com.phongvo.estatespringboot.service;

import com.phongvo.estatespringboot.dto.UserDTO;
import com.phongvo.estatespringboot.dto.UserResponse;
import com.phongvo.estatespringboot.entity.Building;
import com.phongvo.estatespringboot.entity.User;
import com.phongvo.estatespringboot.exceptions.BuildingNotFoundException;
import com.phongvo.estatespringboot.mapper.UserMapper;
import com.phongvo.estatespringboot.repository.BuildingRepository;
import com.phongvo.estatespringboot.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BuildingRepository buildingRepository;

    public UserResponse save(UserDTO userDTO){
        User user = userMapper.dtoToEntity(userDTO);
        User old = userRepository.findById(userDTO.getId())
                .orElseThrow(()->new UsernameNotFoundException("NOT FOUND USER ID " + userDTO.getId()));
        user.setCreatedDate(old.getCreatedDate());
        user.setCreatedBy(old.getCreatedBy());
        user.setBuildingPriorities(old.getBuildingPriorities());
        user.setBuildings(old.getBuildings());
        user.setCustomers(old.getCustomers());
        user.setPassword(old.getPassword());
        user.setUsername(old.getUsername());
        user.setRoles(old.getRoles());
        user.setEnable(true);
        return userMapper.entityToResponse(userRepository.save(user));
    }

    public List<UserResponse> findAll() {
        return userMapper.listEntityToResponseList(userRepository.findAllByEnable(true));
    }

    public List<UserResponse> findAllStaffs() {
        return userMapper.listEntityToResponseList(userRepository.findAllByEnableAndRoles_Code(true, "STAFF"));
    }

    public List<UserResponse> findAllByBuildingId(Long id) {
        List<UserResponse> results = new ArrayList<>();
        List<User> users = userRepository.findAllByEnableAndRoles_Code(true,"STAFF");
        for(User user : users){
            UserResponse userResponse = userMapper.entityToResponse(user);
            if(userRepository.existsByIdAndBuildings_Id(user.getId(), id)){
                userResponse.setChecked("checked");
            }else{
                userResponse.setChecked("");
            }
            results.add(userResponse);
        }
        return results;
    }
}
