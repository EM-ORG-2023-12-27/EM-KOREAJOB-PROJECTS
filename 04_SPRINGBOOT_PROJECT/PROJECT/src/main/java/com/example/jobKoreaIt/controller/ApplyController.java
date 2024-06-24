package com.example.jobKoreaIt.controller;

import com.example.jobKoreaIt.domain.common.entity.Apply;
import com.example.jobKoreaIt.domain.common.service.ApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;

    @PostMapping
    public ResponseEntity<Apply> applyForJob(@RequestParam Long jobSeekerId, @RequestParam Long resumeId, @RequestParam String jobTitle, @RequestParam String companyName) {
        Apply apply = applyService.applyForJob(jobSeekerId, resumeId, jobTitle, companyName);
        return ResponseEntity.ok(apply);
    }
}
