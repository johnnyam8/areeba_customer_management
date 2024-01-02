package com.areeba.areeba_customer_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MobileVerificationResponseDTO {
    // indicate if the mobile number is valid
    private Boolean isValid;
    private MobileNumberDetailsDTO mobileNumberDetailsDTO;
}
