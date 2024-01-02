package com.areeba.areeba_customer_management.service.impl;

import com.areeba.areeba_customer_management.model.dto.CustomerDTO;
import com.areeba.areeba_customer_management.model.entity.Customer;
import com.areeba.areeba_customer_management.service.MappingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingServiceImpl implements MappingService {
    /**
     * merge customerDTO and return new customer entity
     * @author Johnny
     * @param customerDTO
     * @return
     */
    @Override
    public  Customer DtoToEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setFatherName(customerDTO.getFatherName());
        customer.setLastName(customerDTO.getLastName());
        customer.setAddress(customerDTO.getAddress());
        customer.setMobileNumber(customerDTO.getMobileNumber());
        return customer;
    }

    /**
     * merge customer entity  and return new customerDTO
     *
     * @Author Johnny
     * @param customer
     * @return
     */
    @Override
    public CustomerDTO entityToDto(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setFatherName(customer.getFatherName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> entityToDtoList(List<Customer> customers){
        List<CustomerDTO> customersDTO = new ArrayList<>();
        for(Customer customer: customers){
            CustomerDTO customerDTO = entityToDto(customer);
            customersDTO.add(customerDTO);
        }
        return customersDTO;
    }

}
