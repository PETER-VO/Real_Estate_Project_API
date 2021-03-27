package com.phongvo.estatespringboot.controller;


import com.phongvo.estatespringboot.dto.CustomerDto;
import com.phongvo.estatespringboot.dto.CustomerResponse;
import com.phongvo.estatespringboot.dto.Ids;
import com.phongvo.estatespringboot.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping(value = "/api/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.save(customerDto), OK);
    }

    @GetMapping
    public List<CustomerResponse> getCustomer(@RequestBody CustomerDto customerDto){
        return customerService.findAll(customerDto);
    }

    @PutMapping
    public ResponseEntity<Object> editCustomer(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.save(customerDto), OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Object> getCustomerById(@PathVariable("customerId") Long id){
        return new ResponseEntity<>(customerService.findCustomerById(id), OK);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteCustomer(@RequestBody Ids ids){
        customerService.delete(ids);
        return new ResponseEntity<>(OK);
    }

}
