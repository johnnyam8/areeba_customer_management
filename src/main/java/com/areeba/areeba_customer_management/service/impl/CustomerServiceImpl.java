package com.areeba.areeba_customer_management.service.impl;

import com.areeba.areeba_customer_management.exception.InvalidMobileNumberException;
import com.areeba.areeba_customer_management.model.dto.CustomerDTO;
import com.areeba.areeba_customer_management.model.dto.MobileVerificationResponseDTO;
import com.areeba.areeba_customer_management.model.entity.Customer;
import com.areeba.areeba_customer_management.repository.CustomerRepository;
import com.areeba.areeba_customer_management.service.CustomerService;
import com.areeba.areeba_customer_management.service.MappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.webjars.NotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {


    @Value("${mobileservice.base.url}")
    private String mobileServiceBaseUrl;

    @Value("${mobileservice.base.verification.path}")
    private String mobileServiceVerificationPath;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MappingService mappingService;

    /**
     * Add new customer
     * @param customerDTO
     */
    @Override
    public void addCustomer(CustomerDTO customerDTO) throws InvalidMobileNumberException {
        Customer customer = mappingService.DtoToEntity(customerDTO);
        // if mobile number is valid, continue the save operation
        if(verifyMobileNumber(customer.getMobileNumber()))
            customerRepository.save(customer);
        else {
            throw new InvalidMobileNumberException("Mobile Number " +customer.getMobileNumber() + " is not a valid mobile number");
        }
    }

    /**
     * update existing customer
     * @param customerDTO
     */
    @Override
    public void updateCustomer(CustomerDTO customerDTO) throws InvalidMobileNumberException {
        Customer customer = mappingService.DtoToEntity(customerDTO);
        if (customerRepository.findById(customer.getId()).isPresent()){
            // if mobile number is valid, continue the save operation
            if (verifyMobileNumber(customer.getMobileNumber()))
                customerRepository.save(customer);
            else
                throw new InvalidMobileNumberException("Mobile Number " + customer.getMobileNumber() + " is not a valid mobile number");
        }
        else
            throw new NotFoundException("Could not found customer to update!");
    }

    /**
     * delete customer by Id
     *
     * @Author Johnny
     * @param customerId
     */
    @Override
    public void deleteCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent())
            customerRepository.delete(customer.get());
        else
            throw new NotFoundException("Could not found customer to delete!");

    }

    /**
     *  Return customer by id,
     * @param customerId
     * @return
     */
    @Override
    public CustomerDTO getCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent())
            return mappingService.entityToDto(customer.get());
        else
            throw new NotFoundException("Could not found customer");
    }

    /**
     * get all customers, for simplicity we are returning all customer without paging,
     * however when we have a big data, we need to implement  paging to enhance performance
     * @return
     */
    @Override
    public List<CustomerDTO> getCustomers() {
        List<Customer> customers =  customerRepository.findAll();
        return mappingService.entityToDtoList(customers);
    }


    /**
     *  consume the Mobile verification service and verify the mobile Number
     * @Author Johnny
     * @param mobileNumber
     * @return
     */
    private Boolean verifyMobileNumber(String mobileNumber) throws InvalidMobileNumberException {
        try {
            //using webclient
            MobileVerificationResponseDTO mobileVerificationResponseDTO = WebClient.builder().baseUrl(mobileServiceBaseUrl.trim())
                    .build().get().uri(mobileServiceVerificationPath.trim() + mobileNumber).retrieve().bodyToMono(MobileVerificationResponseDTO.class).block();
            // check response
            if (mobileVerificationResponseDTO == null) {
                log.error("Mobile service verification API return null response");
                throw new InvalidMobileNumberException("Could not validate mobile number");
            }
            // if the returned response isValid, get the result of the mobile details from MobileNumberDetailsDTO
            // and depending on the logic we can do the operations required
            return mobileVerificationResponseDTO.getIsValid();
        }catch (WebClientException e){
            log.error("Error when calling mobile verification service",e);
            throw new InvalidMobileNumberException("Mobile verification service Call failed ");
        }
    }
}
