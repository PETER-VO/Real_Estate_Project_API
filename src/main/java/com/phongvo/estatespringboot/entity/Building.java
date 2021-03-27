package com.phongvo.estatespringboot.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Building extends Base {
    private String name;
    private String ward;
    private String street;
    private String structure;
    private String image;
    private String deposit;
    private String payment;
    private String direction;
    private String level;
    private String district;
    @Column(name = "numberofbasement")
    private Integer numberOfBasement;
    @Column(name = "floorarea")
    private Integer floorarea;
    @Column(name = "rentareadescription")
    private String rentAreaDescription;
    @Column(name = "rentcost")
    private Integer rentCost;
    @Column(name = "costDescription")
    private String costDescription;
    @Column(name = "servicecost")
    private Integer serviceCost;
    @Column(name = "carcost")
    private Integer carCost;
    @Column(name = "motorcost")
    private Integer motoCost;
    private String type;
    @Column(name = "electricbill")
    private String electricBill;
    @Column(name="timerent")
    private String timeRent;
    @Column(name = "timedecoration")
    private String timeDecoration;
    @Column(name = "ownername")
    private String ownerName;
    @Column(name = "ownerphone")
    private String ownerPhone;
    //Relation
    @OneToMany(mappedBy = "building", fetch = LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST})
    private List<RentArea> rentAreas = new ArrayList<>();

    @ManyToMany( fetch = LAZY)
    @JoinTable(name = "assignment_building",
            joinColumns = @JoinColumn(name = "building_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<User> userAssigments = new ArrayList<>();

    @ManyToMany(mappedBy = "buildingPriorities", fetch = LAZY)
    private List<User> userOfBuildingPriorities = new ArrayList<>();
}
