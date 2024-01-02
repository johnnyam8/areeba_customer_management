package com.areeba.areeba_customer_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MobileNumberDetailsDTO {
    private String countryCode;
    private String countryName;
    private String operatorName;
}
