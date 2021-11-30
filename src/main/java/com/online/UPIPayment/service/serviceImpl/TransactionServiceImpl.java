package com.online.UPIPayment.service.serviceImpl;

import com.online.UPIPayment.dto.StatementResponseDTO;
import com.online.UPIPayment.dto.TransactionBankRequestDTO;
import com.online.UPIPayment.entity.CustomerUpi;
import com.online.UPIPayment.entity.TransactionUpi;
import com.online.UPIPayment.exception.UserDefinedException;
import com.online.UPIPayment.feignClient.FundTransferBankClient;
import com.online.UPIPayment.repository.CustomerRespository;
import com.online.UPIPayment.repository.TransactionRespository;
import com.online.UPIPayment.service.TransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    FundTransferBankClient fundTransferBankClient;

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    @Autowired
    TransactionRespository transactionRespository;

    @Autowired
    CustomerRespository customerRespository;

    @Override
    public String fundTransfer(TransactionBankRequestDTO transactionBankRequestDTO) throws UserDefinedException{

        ResponseEntity<Boolean> fundTransferStatus = fundTransferBankClient.
                fundTransferViaPhone(transactionBankRequestDTO);

        if(fundTransferStatus.getBody()!=null && fundTransferStatus.getBody()){

            TransactionUpi transactionUpiPhoneNumber = new TransactionUpi();
            TransactionUpi transactionUpiTransactionPhoneNumber = new TransactionUpi();

            CustomerUpi customerUpiFrom = new CustomerUpi();
            CustomerUpi customerUpiTo = new CustomerUpi();

            customerUpiFrom = customerRespository.findByPhoneNumber(transactionBankRequestDTO.getPhoneNumber());
            customerUpiTo = customerRespository.findByPhoneNumber(transactionBankRequestDTO.getTransactionPhoneNumber());

            BeanUtils.copyProperties(transactionBankRequestDTO,transactionUpiPhoneNumber);
            transactionUpiPhoneNumber.setTransactionDate(LocalDateTime.now());
            transactionUpiPhoneNumber.setCustomerUpi(customerUpiFrom);
            transactionUpiPhoneNumber.setTransactionType("DEBIT");
            transactionRespository.save(transactionUpiPhoneNumber);

            BeanUtils.copyProperties(transactionBankRequestDTO,transactionUpiTransactionPhoneNumber);
            transactionUpiTransactionPhoneNumber.setPhoneNumber(transactionBankRequestDTO.getTransactionPhoneNumber());
            transactionUpiTransactionPhoneNumber.setTransactionPhoneNumber(transactionBankRequestDTO.getPhoneNumber());
            transactionUpiTransactionPhoneNumber.setTransactionDate(LocalDateTime.now());
            transactionUpiTransactionPhoneNumber.setCustomerUpi(customerUpiTo);
            transactionUpiTransactionPhoneNumber.setTransactionType("CREDIT");
            transactionRespository.save(transactionUpiTransactionPhoneNumber);
            return "Transaction Successful";
        }
        return "Transaction Unsuccessful";
    }

    @Override
    public List<StatementResponseDTO> getCustomerStatement(Long phoneNumber, int pageNumber, int pageSize) throws UserDefinedException {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "transactionDate"));

        List<StatementResponseDTO> listStatement = new ArrayList<>();

        List<TransactionUpi> listTransaction = transactionRespository.findByPhoneNumber(phoneNumber, pageable);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        for(TransactionUpi transactionUpi:listTransaction) {
            StatementResponseDTO statement=new StatementResponseDTO();
            BeanUtils.copyProperties(transactionUpi, statement);
            statement.setTransactionDate(dtf.format(transactionUpi.getTransactionDate()));
            listStatement.add(statement);
        }

        return listStatement;
    }
}
