package com.phongvo.estatespringboot.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class RentArea extends Base {
  private Integer value;
  //Relation
  @ManyToOne
  @JoinColumn(name = "building_id")
  private Building building;
}
