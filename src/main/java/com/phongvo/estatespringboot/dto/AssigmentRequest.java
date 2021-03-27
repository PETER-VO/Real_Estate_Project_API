package com.phongvo.estatespringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssigmentRequest {
    private Long buildingId;
    private Long customerId;
    private Long[] staffIds;
}
