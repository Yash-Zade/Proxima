package com.teamarc.proxima.services;

import com.teamarc.proxima.entity.College;
import com.teamarc.proxima.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollegeService {
    private final CollegeRepository collegeRepository;

    public College createNewCollege(College college) {
        return collegeRepository.save(college);
    }
}
