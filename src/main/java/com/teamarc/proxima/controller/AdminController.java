package com.teamarc.proxima.controller;

import com.teamarc.proxima.dto.*;
import com.teamarc.proxima.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
@Secured("ROLE_ADMIN")
public class AdminController {

    private final AdminService adminService;


    @PostMapping(path = "onBoardNewEmployer/{userId}")
    public ResponseEntity<EmployerDTO> onBoardNewEmployer(@PathVariable Long userId, @RequestBody OnBoardNewEmployerDTO onBoardNewEmployerDTO) {
        return new ResponseEntity<>(adminService.onboardNewEmployer(userId, onBoardNewEmployerDTO), HttpStatus.CREATED);
    }


    @PostMapping(path = "onBoardNewMentor/{userId}")
    public ResponseEntity<MentorProfileDTO> onBoardNewMentor(@PathVariable Long userId, @RequestBody OnboardNewMentorDTO onboardNewMentorDTO) {
        return new ResponseEntity<>(adminService.onboardNewMentor(userId, onboardNewMentorDTO), HttpStatus.CREATED);
    }

    @PostMapping(path = "onBoardNewCollege/{collegeId}")
    public ResponseEntity<CollegeDTO> onBoardNewCollege(@PathVariable Long collegeId, @RequestBody OnBoardNewCollegeDTO onBoardNewCollegeDTO) {
        return new ResponseEntity<>(adminService.onboardNewCollege(collegeId, onBoardNewCollegeDTO), HttpStatus.CREATED);
    }

    @PostMapping(path = "/reject/employer/{userId}")
    public ResponseEntity<Void> rejectEmployer(@PathVariable Long userId,@RequestBody OnBoardNewEmployerDTO onBoardNewEmployerDTO) {
        adminService.rejectEmployer(userId, onBoardNewEmployerDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/reject/mentor/{userId}")
    public ResponseEntity<Void> rejectMentor(@PathVariable Long userId,@RequestBody OnboardNewMentorDTO onboardNewMentorDTO) {
        adminService.rejectMentor(userId, onboardNewMentorDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/reject/college/{collegeId}")
    public ResponseEntity<Void> rejectCollege(@PathVariable Long collegeId, @RequestBody OnBoardNewCollegeDTO onboardNewCollegeDTO) {
        adminService.rejectCollege(collegeId, onboardNewCollegeDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/totalUsers")
    public ResponseEntity<Long> getTotalUsers() {
        return new ResponseEntity<>(adminService.getTotalUsers(), HttpStatus.OK);
    }

    @GetMapping(path = "/totalEmployers")
    public ResponseEntity<Long> getTotalEmployers() {
        return new ResponseEntity<>(adminService.getTotalEmployers(), HttpStatus.OK);
    }

    @GetMapping(path = "/totalMentors")
    public ResponseEntity<Long> getTotalMentors() {
        return new ResponseEntity<>(adminService.getTotalMentors(), HttpStatus.OK);
    }

    @GetMapping(path = "/requests/employers")
    public ResponseEntity<Page<OnBoardNewEmployerDTO>> getEmployerRequests(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                                           @RequestParam(defaultValue = "10", required = false) Integer pageSize ) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize);
        return new ResponseEntity<>(adminService.getEmployerRequests(pageRequest), HttpStatus.OK);
    }

    @GetMapping(path = "/requests/mentors")
    public ResponseEntity<Page<OnboardNewMentorDTO>> getMentorRequests(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                                      @RequestParam(defaultValue = "10", required = false) Integer pageSize ) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize);
        return new ResponseEntity<>(adminService.getMentorRequests(pageRequest), HttpStatus.OK);
    }
    @GetMapping(path = "/requests/colleges")
    public ResponseEntity<Page<OnBoardNewCollegeDTO>> getCollegeRequests(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                                      @RequestParam(defaultValue = "10", required = false) Integer pageSize ) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize);
        return new ResponseEntity<>(adminService.getCollegeRequests(pageRequest), HttpStatus.OK);
    }

    @GetMapping(path = "/requests")
    public ResponseEntity<Long> getTotalRequests() {
        return new ResponseEntity<>(adminService.getTotalRequests(), HttpStatus.OK);
    }

}
