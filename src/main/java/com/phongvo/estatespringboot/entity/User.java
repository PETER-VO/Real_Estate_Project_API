package com.phongvo.estatespringboot.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends Base{
    private String username;
    private String fullname;
    private String password;
    private boolean enable;
    private String email;
    private String phone;
    private String address;
    //Relation
    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "priority_building",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "building_id", nullable = false))
    private List<Building> buildingPriorities = new ArrayList<>();

    @ManyToMany(fetch = LAZY, mappedBy = "userAssigments")
    private List<Building> buildings = new ArrayList<>();

    @ManyToMany(mappedBy = "users", fetch = LAZY)
    private List<Customer> customers = new ArrayList<>();

    @ManyToMany(fetch = LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false))
    private List<Role> roles = new ArrayList<>();
}
