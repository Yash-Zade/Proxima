package com.teamarc.proxima.services;

import com.teamarc.proxima.dto.*;
import com.teamarc.proxima.entity.*;
import com.teamarc.proxima.exceptions.ResourceNotFoundException;
import com.teamarc.proxima.repository.ApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ApplicantService {

//    @Value("${base.url}")
//    private String baseUrl;


    private final ApplicantRepository applicantRepository;
    private final ModelMapper modelMapper;


    public ApplicantDTO getApplicantProfile() {
        Applicant applicant = getCurrentApplicant();
        return modelMapper.map(applicant, ApplicantDTO.class);

    }


    public Applicant getCurrentApplicant() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return applicantRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant not associated with user with id: " + user.getId()));

    }

    public Applicant createNewApplicant(User user) {
        Applicant applicant = Applicant.builder()
                .user(user)
                .jobApplications(null)
                .resume(null)
                .build();
        return applicantRepository.save(applicant);
    }

    public ApplicantDTO updateProfile(Long applicantId, Map<String, Object> updates) {
        Applicant applicant = applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant not found with id: " + applicantId));
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(Applicant.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, applicant, value);
        });
        return modelMapper.map(applicantRepository.save(applicant), ApplicantDTO.class);
    }


    public void uploadResume(MultipartFile file) {
        Applicant applicant = getCurrentApplicant();
        applicant.setResume(file.getOriginalFilename());
        applicantRepository.save(applicant);
    }


    public ApplicantDTO getApplicantById(Long applicantId) {
        return modelMapper.map(applicantRepository.findById(applicantId)
                .orElseThrow(() -> new ResourceNotFoundException("Applicant not found with id: " + applicantId)), ApplicantDTO.class);
    }




    public boolean isOwnerOfProfile(Long applicantId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApplicantDTO applicant = getApplicantById(applicantId);
        User applicantUser = modelMapper.map(applicant.getUser(), User.class);
        return user.equals(applicantUser);
    }


}