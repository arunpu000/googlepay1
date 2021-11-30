package com.online.UPIPayment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementResponseDTO{
    private long transactionId;

    private String transactionDate;

    private Long phoneNumber;

    private String transactionType;

    private Long transactionPhoneNumber;

    private Long amount;

    private String message;

}
