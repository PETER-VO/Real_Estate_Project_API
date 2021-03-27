package com.phongvo.estatespringboot.enums;

import java.util.stream.Stream;

public enum BuildingTypeEnum {

    GROUND_FLOOR("Ground Floor"),
    DETACHED_HOUSE("Detached House"),
    FURNISHED_HOUSE("Furnished House");

    private String buildingTypeName;

    BuildingTypeEnum(String districtFullName) {
        this.buildingTypeName = districtFullName;
    }

    // standard getters and setters

    public static Stream<BuildingTypeEnum> stream() {
        return Stream.of(BuildingTypeEnum.values());
    }

    public String getBuildingTypeName() {
        return buildingTypeName;
    }

}
