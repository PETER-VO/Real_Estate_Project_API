package com.phongvo.estatespringboot.mapper;

import com.phongvo.estatespringboot.dto.CustomerDto;
import com.phongvo.estatespringboot.dto.CustomerResponse;
import com.phongvo.estatespringboot.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CustomerMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Customer dtoToEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, Customer.class);
    }

    public CustomerResponse entityToResponse(Customer customer){
        CustomerResponse customerResponse = modelMapper.map(customer, CustomerResponse.class);
        customerResponse.setInChargeStaffs(customer.getUsers().stream().map(user-> String.valueOf(user.getId())).collect(Collectors.joining(",")));
        return customerResponse;
    }

    public List<CustomerResponse> listEntityToListDto(List<Customer> customers){
        List<CustomerResponse> customerResponseList = new ArrayList<>();
        for(Customer customer : customers){
            CustomerResponse customerResponse = entityToResponse(customer);
            customerResponseList.add(customerResponse);
        }
        return customerResponseList;
    }
}
