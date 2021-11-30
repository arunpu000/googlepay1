package com.online.UPIPayment.repository;


import com.online.UPIPayment.entity.CustomerUpi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository<CustomerUpi,Long> {

    CustomerUpi findByPhoneNumber(Long Phone);
}
