package com.online.UPIPayment.feignClient;

import com.online.UPIPayment.dto.TransactionBankRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;


@FeignClient(name = "http://Bank-Service/bank/customerregistration")
public interface CustomerRegisterBankClient {

    @GetMapping("/checkCustomerExist/{phoneNumber}")
    public ResponseEntity<Boolean> customerExist(@PathVariable Long phoneNumber);

    @GetMapping("/checkServiceStatus")
    public String getServiceStatus();
}
