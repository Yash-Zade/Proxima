package com.teamarc.proxima.controller;

import com.teamarc.proxima.dto.*;
import com.teamarc.proxima.services.ApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/applicants")
@Secured("ROLE_APPLICANT")
public class ApplicantController {

    private final ApplicantService applicantService;


    @GetMapping(path = "/profile")
    public ResponseEntity<ApplicantDTO> getApplicantProfile() {
        return ResponseEntity.ok(applicantService.getApplicantProfile());
    }

    @PreAuthorize("@applicantService.isOwnerOfProfile(#id)")
    @PutMapping(path = "/profile/{id}")
    public ResponseEntity<ApplicantDTO> updateProfile(@RequestBody Map<String, Object> object, @PathVariable Long id) {
        return ResponseEntity.ok(applicantService.updateProfile(id, object));
    }



    @PostMapping(path = "/resume/upload")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        applicantService.uploadResume(file);
        return ResponseEntity.ok("Resume uploaded successfully");
    }

}