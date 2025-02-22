package com.teamarc.proxima.repository;


import com.teamarc.proxima.entity.Applicant;
import com.teamarc.proxima.entity.JobApplication;
import com.teamarc.proxima.entity.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}
