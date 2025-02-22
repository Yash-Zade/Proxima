package com.teamarc.proxima.repository;

import com.teamarc.proxima.entity.User;
import com.teamarc.proxima.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
