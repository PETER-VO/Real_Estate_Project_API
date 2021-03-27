package com.phongvo.estatespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDto {
    private Long id;
    private String name;
    private String ward;
    private String street;
    private String structure;
    private Integer numberOfBasement;
    private Integer floorarea;
    private String direction;
    private String level;
    private String rentAreaDescription;
    private String district;
    private Integer rentCost;
    private String costDescription;
    private Integer serviceCost;
    private Integer carCost;
    private Integer motoCost;
    private String type;
    private String electricBill;
    private String deposit;
    private String payment;
    private String timeRent;
    private String timeDecoration;
    private String ownerName;
    private String ownerPhone;
    private String image;
    private String rentArea;
    private Integer areaFrom;
    private Integer areaTo;
    private Integer costFrom;
    private Integer costTo;
    private Long staffId;
    private String staffName;
    private String[] types;
    private Integer offSet = 1;
    private Integer limit = 7;
}
