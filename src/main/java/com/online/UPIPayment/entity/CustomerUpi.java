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
@Table(name = "CustomerUpi")
public class CustomerUpi {

    @Id
    @SequenceGenerator(name = "customer_id", sequenceName = "CustomerUpi_Sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id")
    @Column(name = "id")
    private long customerId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "phoneNumber")
    private Long phoneNumber;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;
}
