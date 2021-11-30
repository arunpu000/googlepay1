package com.online.UPIPayment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TransactionUpi")
public class TransactionUpi {

    @Id
    @SequenceGenerator(name = "transaction_id", sequenceName = "TransactionUpi_Sequence", initialValue = 2000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id")
    @Column(name = "id")
    private long transactionId;

    @Column(name = "transactionDate")
    private LocalDateTime transactionDate;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Column(name = "transactionPhoneNumber")
    private Long transactionPhoneNumber;

    @Column(name = "transactionType")
    private String transactionType;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "message")
    private String message;

    @ManyToOne
    private CustomerUpi customerUpi;
}
