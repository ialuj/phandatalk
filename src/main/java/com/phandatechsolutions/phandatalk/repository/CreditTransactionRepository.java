package com.phandatechsolutions.phandatalk.repository;

import com.phandatechsolutions.phandatalk.model.CreditTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditTransactionRepository extends JpaRepository<CreditTransactionEntity, Long> {
}
