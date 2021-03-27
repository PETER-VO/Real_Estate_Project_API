package com.phongvo.estatespringboot.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Base{
    private String email;
    private String phone;
    private String demand;
    private boolean enable;
    private String fullname;
    //Relation
    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "assignment_customer",
            joinColumns = @JoinColumn(name = "customer_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<User> users = new ArrayList<>();
}
