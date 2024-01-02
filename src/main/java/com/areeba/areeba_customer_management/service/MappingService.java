package com.areeba.areeba_customer_management.service;

import com.areeba.areeba_customer_management.model.dto.CustomerDTO;
import com.areeba.areeba_customer_management.model.entity.Customer;

import java.util.List;

/**
 * service for mapping DTO to entities and vice versa
 *
 * hint this could be replaced by libraries like mapping struct
 *
 * @Author Johnny
 */
public interface MappingService {

    Customer DtoToEntity(CustomerDTO customerDTO);

    CustomerDTO entityToDto(Customer customer);

    List<CustomerDTO> entityToDtoList(List<Customer> customers);
}
