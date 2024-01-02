package com.areeba.areeba_customer_management.model.entity;

import com.areeba.areeba_customer_management.model.dto.CustomerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * entity that represent the customer info
 */
@Entity
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String fatherName;
    private String LastName;
    private String address;
    private String mobileNumber;


}
