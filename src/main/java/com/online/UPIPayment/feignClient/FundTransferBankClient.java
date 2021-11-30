package com.online.UPIPayment.feignClient;

import com.online.UPIPayment.dto.TransactionBankRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "http://Bank-Service/bank/fundTransfer")
public interface FundTransferBankClient {

    @PostMapping("/fundTransferViaPhone")
    public ResponseEntity<Boolean> fundTransferViaPhone
            (@RequestBody TransactionBankRequestDTO transactionBankRequestDTO);
}
