package com.teamarc.proxima.services;

import com.teamarc.proxima.entity.Mentor;
import com.teamarc.proxima.repository.MentorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorService {
    private final MentorRepository mentorRepository;

    public Mentor createNewMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }

}
