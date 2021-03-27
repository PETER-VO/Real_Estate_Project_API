package com.phongvo.estatespringboot.repository;

import com.phongvo.estatespringboot.entity.Customer;
import com.phongvo.estatespringboot.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>, CustomerRepositoryCustom {

}
