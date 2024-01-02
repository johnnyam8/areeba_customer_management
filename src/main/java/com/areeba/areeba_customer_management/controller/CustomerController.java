package com.areeba.areeba_customer_management.controller;

import com.areeba.areeba_customer_management.exception.InvalidMobileNumberException;
import com.areeba.areeba_customer_management.model.dto.CustomerDTO;
import com.areeba.areeba_customer_management.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name= "CustomerController", description = "Operations to handle Customer Crud")
@RestController
@RequestMapping("/customer/v1")
@Validated
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Create new customer")
    @PostMapping()
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws InvalidMobileNumberException {
        log.info("Process Start - creating new customer");
        customerService.addCustomer(customerDTO);
        log.info("Process End- creating new customer");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update customer info")
    @PutMapping()
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) throws InvalidMobileNumberException {
        log.info("Process Start - updating  customer {}",customerDTO.getId());
        customerService.updateCustomer(customerDTO);
        log.info("Process End - updating  customer {}",customerDTO.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete existing customer")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long customerId){
        log.info("Process Start - delete  customer {}",customerId);
        customerService.deleteCustomer(customerId);
        log.info("Process End - delete  customer {}",customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Return customer details")
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") Long customerId){
        log.info("Process Start - get customer");
        CustomerDTO customerDTO = customerService.getCustomer(customerId);
        log.info("Process End - get customer");
        return new ResponseEntity<>(customerDTO,HttpStatus.OK);
    }

    @Operation(summary = "Return all customers")
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomer(){
        log.info("Process Start - get All customer");
        List<CustomerDTO> customerDTOList = customerService.getCustomers();
        log.info("Process End - get All customer");
        return new ResponseEntity<>(customerDTOList,HttpStatus.OK);
    }
}
