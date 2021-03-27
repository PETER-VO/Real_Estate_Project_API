package com.phongvo.estatespringboot.repository.custom;

import com.phongvo.estatespringboot.dto.CustomerDto;
import com.phongvo.estatespringboot.entity.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<Customer> findAll(CustomerDto customerDto);
}
