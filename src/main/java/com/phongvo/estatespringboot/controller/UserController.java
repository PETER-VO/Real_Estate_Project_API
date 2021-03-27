package com.phongvo.estatespringboot.controller;


import com.phongvo.estatespringboot.dto.UserDTO;
import com.phongvo.estatespringboot.dto.UserResponse;
import com.phongvo.estatespringboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping
    public ResponseEntity<Object> editUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.save(userDTO) , HttpStatus.OK);
    }

    @GetMapping
    public List<UserResponse> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/staff")
    public List<UserResponse> getStaffs(){
        return userService.findAllStaffs();
    }


    @GetMapping("/{buildingId}")
    public List<UserResponse> getUsersByBuildingId(@PathVariable("buildingId") Long id){
        return userService.findAllByBuildingId(id);
    }
}
