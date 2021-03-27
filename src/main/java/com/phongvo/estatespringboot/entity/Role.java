package com.phongvo.estatespringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role extends Base{
    private String name;
    private String code;
    //Relation
    @ManyToMany(mappedBy = "roles", fetch = LAZY)
    private List<User> users = new ArrayList<>();

}
