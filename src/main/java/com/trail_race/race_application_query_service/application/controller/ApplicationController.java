package com.trail_race.race_application_query_service.application.controller;

import com.trail_race.race_application_query_service.application.dto.ApplicationResponse;
import com.trail_race.race_application_query_service.application.dto.ApplicationsResponse;
import com.trail_race.race_application_query_service.application.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponse> getApplication(@PathVariable String applicationId) {
        return ResponseEntity.ok(applicationService.getApplicationById(applicationId));
    }

    @GetMapping
    public ResponseEntity<ApplicationsResponse> getApplications() {
        return ResponseEntity.ok(applicationService.findAll());
    }
}
