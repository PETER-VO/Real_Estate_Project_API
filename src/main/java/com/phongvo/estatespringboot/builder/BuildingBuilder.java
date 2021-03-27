package com.phongvo.estatespringboot.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildingBuilder {
    private String name;
    private Integer floorArea;
    private String district;
    private String ward;
    private String street;
    private Integer areaFrom;
    private Integer areaTo;
    private Integer numberOfBasement;
    private String direction;
    private String level;
    private Integer costFrom;
    private Integer costTo;
    private String ownerName;
    private String ownerPhone;
    private String[] types = new String[]{};
    private Long staffId;
}
