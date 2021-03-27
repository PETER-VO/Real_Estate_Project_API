package com.phongvo.estatespringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends Base{
    private String type;
    private String note;
    //Relation
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
