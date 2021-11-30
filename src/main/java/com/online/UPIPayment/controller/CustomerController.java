package com.online.UPIPayment.controller;

import com.online.UPIPayment.dto.CustomerRequestDTO;
import com.online.UPIPayment.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/bank-service-status")
    public String getBankServiceStatus() {
        return customerService.getCustomerRegisterBankClient();
    }

    @PostMapping("/registration")
    public String userRegistration(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        String registration=customerService.customerRegistration(customerRequestDTO);
        return registration;
    }
}
