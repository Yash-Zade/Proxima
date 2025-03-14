package com.teamarc.proxima.repository;

import com.teamarc.proxima.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletTransactionsRepository extends JpaRepository<WalletTransaction, Long> {
    Optional<WalletTransaction> findByTransactionId(String transactionId);
}
