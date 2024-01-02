package com.areeba.areeba_customer_management.service;

import com.areeba.areeba_customer_management.exception.InvalidMobileNumberException;
import com.areeba.areeba_customer_management.model.dto.CustomerDTO;
import com.areeba.areeba_customer_management.model.entity.Customer;

import java.util.List;

/**
 * customer service for handling basic crud operations
 * @Author Johnny
 */
public interface CustomerService {

    void addCustomer(CustomerDTO customerDTO) throws InvalidMobileNumberException;

    void updateCustomer(CustomerDTO customerDTO) throws InvalidMobileNumberException;

    void deleteCustomer(Long customerId);

    CustomerDTO getCustomer(Long customerId);

    List<CustomerDTO> getCustomers();

}
