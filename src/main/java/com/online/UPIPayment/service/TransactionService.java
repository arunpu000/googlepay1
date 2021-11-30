package com.online.UPIPayment.service;

import com.online.UPIPayment.dto.StatementResponseDTO;
import com.online.UPIPayment.dto.TransactionBankRequestDTO;
import com.online.UPIPayment.exception.UserDefinedException;

import java.util.List;

public interface TransactionService {

    String fundTransfer(TransactionBankRequestDTO transactionBankRequestDTO) throws UserDefinedException;
    List<StatementResponseDTO> getCustomerStatement(Long phoneNumber,int pageNumber, int pageSize) throws UserDefinedException;

}
