package com.teamarc.proxima.repository;

import com.teamarc.leaflink.entity.OnBoardNewStartup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface nBoardNewStartupRepository extends JpaRepository<OnBoardNewStartup, Long> {
}