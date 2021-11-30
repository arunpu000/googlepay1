package com.online.UPIPayment.service;

import com.online.UPIPayment.dto.CustomerRequestDTO;

public interface CustomerService {

    String getCustomerRegisterBankClient();

    String customerRegistration(CustomerRequestDTO customerRequestDTO);
}
