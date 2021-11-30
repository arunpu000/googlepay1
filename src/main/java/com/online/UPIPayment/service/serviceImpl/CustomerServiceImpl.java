package com.online.UPIPayment.service.serviceImpl;

import com.online.UPIPayment.dto.CustomerRequestDTO;
import com.online.UPIPayment.entity.CustomerUpi;
import com.online.UPIPayment.exception.UserDefinedException;
import com.online.UPIPayment.feignClient.CustomerRegisterBankClient;
import com.online.UPIPayment.repository.CustomerRespository;
import com.online.UPIPayment.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRegisterBankClient customerRegisterBankClient;

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    CustomerRespository customerRespository;

    @Override
    public String getCustomerRegisterBankClient() throws UserDefinedException{
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        System.out.println("Bank Port Number :: "+customerRegisterBankClient.getServiceStatus());
        return circuitBreaker.run(() -> customerRegisterBankClient.getServiceStatus(), throwable -> getDefaultInfo());
    }

    @Transactional
    @Override
    public String customerRegistration(CustomerRequestDTO customerRequestDTO) throws UserDefinedException {


            ResponseEntity<Boolean> phoneNoExist = customerRegisterBankClient.
                    customerExist(customerRequestDTO.getPhoneNumber());

            if (phoneNoExist.getBody() != null && phoneNoExist.getBody()) {

                CustomerUpi customerUpi = new CustomerUpi();
                BeanUtils.copyProperties(customerRequestDTO, customerUpi);
                customerUpi.setCreationDate(LocalDateTime.now());

                CustomerUpi customerRegister = customerRespository.
                        findByPhoneNumber(customerRequestDTO.getPhoneNumber());

                if (customerRegister != null) {
                    return "Phone number already registered.";
                } else {
                    customerRespository.save(customerUpi);
                }
                return "Customer sucessfully register with UPI";
            }
            else{
                return "Customer does not link with any account";
            }


    }

    public String getDefaultInfo()
    {
        return "Bank-service is down, Please try after some time.";
    }

}
