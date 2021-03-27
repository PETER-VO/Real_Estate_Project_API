package com.phongvo.estatespringboot.mapper;

import com.phongvo.estatespringboot.dto.RegisterRequest;
import com.phongvo.estatespringboot.dto.UserDTO;
import com.phongvo.estatespringboot.dto.UserResponse;
import com.phongvo.estatespringboot.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    public User dtoToEntity(RegisterRequest registerRequest){
        return modelMapper.map(registerRequest,User.class);
    }

    public User dtoToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO,User.class);
    }

    public UserResponse entityToResponse(User user){
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setInChargeBuilding(user.getBuildings().stream().map(building -> String.valueOf(building.getId())).collect(Collectors.joining(",")));
        userResponse.setPriorBuildings(user.getBuildingPriorities().stream().map(building -> String.valueOf(building.getId())).collect(Collectors.joining(",")));
        userResponse.setRole(user.getRoles().stream().map(role -> role.getCode()).collect(Collectors.joining(",")));
        userResponse.setInChargeCustomer(user.getCustomers().stream().map(customer -> String.valueOf(customer.getId())).collect(Collectors.joining(",")));
        return userResponse;
    }

    public List<UserResponse> listEntityToResponseList(List<User> users){
        List<UserResponse> results = new ArrayList<>();
        for(User user : users){
            UserResponse userResponse = entityToResponse(user);
            results.add(userResponse);
        }
        return results;
    }

}
