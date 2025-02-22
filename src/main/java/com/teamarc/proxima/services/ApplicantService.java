package com.teamarc.proxima.services;

import com.teamarc.proxima.entity.Applicant;
import com.teamarc.proxima.entity.User;
import com.teamarc.proxima.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicantService {


    private final ApplicantRepository applicantRepository;


    public Applicant createNewApplicant(User user) {
        Applicant applicant = Applicant.builder()
                .user(user)
                .jobApplications(null)
                .resume(null)
                .build();
        return applicantRepository.save(applicant);
    }
}
