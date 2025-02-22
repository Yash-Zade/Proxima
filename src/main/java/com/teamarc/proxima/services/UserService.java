package com.teamarc.proxima.services;


import com.teamarc.proxima.dto.*;
import com.teamarc.proxima.entity.*;
import com.teamarc.proxima.entity.enums.Role;
import com.teamarc.proxima.exceptions.ResourceNotFoundException;
import com.teamarc.proxima.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final OnboardNewEmployerRepository onboardNewEmployerRepository;
    private final OnboardNewMentorRepository onboardNewMentorRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ApplicantService applicantService;
    private final OnBoardNewCollegeRepository onBoardNewCollegeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    public void requestEmployerOnboard(OnBoardNewEmployerDTO onboardNewEmployerDTO) {
        onboardNewEmployerRepository.save(modelMapper.map(onboardNewEmployerDTO, OnboardNewEmployer.class));
    }

    public void requestMentorOnboard(OnboardNewMentorDTO onboardNewMentorDTO) {
        onboardNewMentorRepository.save(modelMapper.map(onboardNewMentorDTO, OnboardNewMentor.class));
    }

    public User loadUserByRole(Role role) {
        return userRepository.findByRoles(role);
    }


    public void requestApplicantOnboard(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        applicantService.createNewApplicant(user);
    }

    public void requestCollegeOnboard(OnBoardNewCollegeDTO collegeRequestDTO) {
        onBoardNewCollegeRepository.save(modelMapper.map(collegeRequestDTO, OnBoardNewCollege.class));
    }
}
