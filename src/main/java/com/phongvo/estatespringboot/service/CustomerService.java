package com.phongvo.estatespringboot.service;

import com.phongvo.estatespringboot.dto.CustomerDto;
import com.phongvo.estatespringboot.dto.CustomerResponse;
import com.phongvo.estatespringboot.dto.Ids;
import com.phongvo.estatespringboot.entity.Customer;
import com.phongvo.estatespringboot.exceptions.CustomerNotFoundException;
import com.phongvo.estatespringboot.mapper.CustomerMapper;
import com.phongvo.estatespringboot.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerResponse save(CustomerDto customerDto){
        Customer customer =  customerMapper.dtoToEntity(customerDto);
        if(customerDto.getId() != null){
            Customer old = customerRepository.findById(customerDto.getId())
                    .orElseThrow(()->new CustomerNotFoundException("Not Found Customer Id " + customerDto.getId()));
            customer.setCreatedBy(old.getCreatedBy());
            customer.setCreatedDate(old.getCreatedDate());
            customer.setUsers(old.getUsers());
        }
        customer.setEnable(true);
        return customerMapper.entityToResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> findAll(CustomerDto customerDto) {
        List<Customer> customers = customerRepository.findAll(customerDto);
        return customerMapper.listEntityToListDto(customers);
    }

    public CustomerResponse findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Not Found Customer By Id " + id));
        return  customerMapper.entityToResponse(customer);
    }

    public void delete(Ids ids) {
        for(Long id : ids.getIds()){
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new CustomerNotFoundException("Not Found Customer By Id " + id));
            customer.setEnable(false);
            customerRepository.save(customer);
        }
    }
}
