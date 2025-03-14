package com.teamarc.proxima.repository;

import com.teamarc.proxima.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Page<Job> findByPostedBy_EmployerId(Long employerId, PageRequest pageRequest, Pageable pageable);
}