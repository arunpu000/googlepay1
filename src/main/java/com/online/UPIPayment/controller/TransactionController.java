package com.online.UPIPayment.controller;

import com.online.UPIPayment.dto.StatementResponseDTO;
import com.online.UPIPayment.dto.TransactionBankRequestDTO;
import com.online.UPIPayment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@Validated
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/fundTransfer")
    public ResponseEntity<String> fundTransferViaPhone(@Valid @RequestBody TransactionBankRequestDTO transactionBankRequestDTO)
    {
        String response = transactionService.fundTransfer(transactionBankRequestDTO);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/Statement")
    public ResponseEntity<List<StatementResponseDTO>> getStatement(@RequestParam @NotNull  Long phoneNumber,
                                                                   @RequestParam @NotNull int pageNumber,
                                                                   @RequestParam @NotNull int pageSize) {
        List<StatementResponseDTO> response = transactionService.getCustomerStatement(phoneNumber, pageNumber, pageSize);
        return new ResponseEntity<List<StatementResponseDTO>>(response, HttpStatus.OK);
    }
}
