package com.phongvo.estatespringboot.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String priorBuildings;
    private String inChargeBuilding;
    private String role;
    private String inChargeCustomer;
    private String checked;
}
