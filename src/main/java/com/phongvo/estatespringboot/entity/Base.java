package com.phongvo.estatespringboot.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Base implements Serializable {

    @Id
    @GeneratedValue(strategy= IDENTITY)
    private Long id;
    @CreatedDate
    @Column(name = "createddate")
    private Date createdDate;
    @CreatedBy
    @Column(name = "createdby")
    private String createdBy;
    @LastModifiedDate
    @Column(name = "modifieddate")
    private Date modifiedDate;
    @LastModifiedBy
    @Column(name = "modifiedby")
    private String modifiedBy;
}
