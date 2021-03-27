package com.phongvo.estatespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingResponse {
    private Long id;
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
    private Integer numberOfBasement;
    private Integer floorarea;
    private String rentAreaDescription;
    private Integer rentCost;
    private String costDescription;
    private Integer serviceCost;
    private Integer carCost;
    private Integer motoCost;
    private String type;
    private String electricBill;
    private String timeRent;
    private String timeDecoration;
    private String ownerName;
    private String ownerPhone;
    private String rentArea;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
    private String inChargeStaffs;
}
