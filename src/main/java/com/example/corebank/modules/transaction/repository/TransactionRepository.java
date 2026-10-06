package com.example.corebank.modules.transaction.repository;

import com.example.corebank.modules.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
