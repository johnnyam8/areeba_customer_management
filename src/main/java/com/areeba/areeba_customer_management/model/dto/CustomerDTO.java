package com.areeba.areeba_customer_management.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
/**
 * customer dto
 */
public class CustomerDTO {
    private Long id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String fatherName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String address;
    @NotEmpty
    private String mobileNumber;
}
