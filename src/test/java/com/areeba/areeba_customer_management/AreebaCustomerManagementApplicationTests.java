package com.areeba.areeba_customer_management;

import com.areeba.areeba_customer_management.exception.InvalidMobileNumberException;
import com.areeba.areeba_customer_management.model.dto.CustomerDTO;
import com.areeba.areeba_customer_management.repository.CustomerRepository;
import com.areeba.areeba_customer_management.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@Transactional
public class AreebaCustomerManagementApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CustomerRepository customerRepository;

    @Test
	@Rollback
	public void createCustomerWithValidMobile() throws InvalidMobileNumberException {
		CustomerDTO customerDTO = CustomerDTO.builder().id(1l).firstName("firstNameTest")
				.fatherName("fatherTest").lastName("lastNameTest").address("addressTest")
				.mobileNumber("+96171961323").build();

		customerService.addCustomer(customerDTO);
		customerRepository.findById(1l);
		assertTrue("customer created",customerRepository.findById(1l).isPresent());

	}

	@Test
	@Rollback
	public void validateCustomerWithNoValidMobile() throws InvalidMobileNumberException {
		CustomerDTO customerDTO = CustomerDTO.builder().id(1l).firstName("firstNameTest")
				.fatherName("fatherTest").lastName("lastNameTest").address("addressTest")
				.mobileNumber("71961323").build();
		try{
			customerService.addCustomer(customerDTO);
			fail("Exception not thrown");
		}catch (InvalidMobileNumberException e){
			assertEquals("Mobile Number "+customerDTO.getMobileNumber()+" is not a valid mobile number", e.getMessage());
		}

	}

	@Test
	@Rollback
	public void updateValidCustomer() throws InvalidMobileNumberException {
		CustomerDTO customerDTO = CustomerDTO.builder().id(1l).firstName("firstNameTest")
				.fatherName("fatherTest").lastName("lastNameTest").address("addressTest")
				.mobileNumber("+96171961323").build();
		 customerService.addCustomer(customerDTO);
		 assertTrue("customer created",customerRepository.findById(1l).isPresent());
		 //try to update the father name and check the test
		customerDTO.setFatherName("updatedFatherName");
		customerService.updateCustomer(customerDTO);
		assertEquals("updatedFatherName",customerRepository.findById(1l).get().getFatherName());
	}

	@Test
	@Rollback
	public void deleteExistingCustomer() throws InvalidMobileNumberException {
		CustomerDTO customerDTO = CustomerDTO.builder().id(1l).firstName("firstNameTest")
				.fatherName("fatherTest").lastName("lastNameTest").address("addressTest")
				.mobileNumber("+96171961323").build();
		customerService.addCustomer(customerDTO);
		assertTrue("customer created",customerRepository.findById(1l).isPresent());
		customerService.deleteCustomer(customerDTO.getId());
		assertTrue("customer deleted",!customerRepository.findById(1l).isPresent());
	}




}
