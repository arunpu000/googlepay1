package com.online.UPIPayment.repository;

import com.online.UPIPayment.dto.StatementResponseDTO;
import com.online.UPIPayment.entity.TransactionUpi;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public interface TransactionRespository extends JpaRepository<TransactionUpi, Serializable> {

    public List<TransactionUpi> findByPhoneNumber(Long phoneNumber, Pageable page);

}
