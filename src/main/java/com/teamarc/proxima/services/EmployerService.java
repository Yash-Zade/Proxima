package com.teamarc.proxima.services;

import com.teamarc.proxima.entity.Employer;
import com.teamarc.proxima.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;

    public Employer createNewEmployer(Employer employer) {
        return employerRepository.save(employer);
    }
}
