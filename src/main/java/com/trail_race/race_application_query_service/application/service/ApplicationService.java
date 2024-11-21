package com.trail_race.race_application_query_service.application.service;

import com.trail_race.race_application_query_service.application.dto.ApplicationRequest;
import com.trail_race.race_application_query_service.application.dto.ApplicationResponse;
import com.trail_race.race_application_query_service.application.dto.ApplicationsResponse;
import com.trail_race.race_application_query_service.application.mapper.ApplicationMapper;
import com.trail_race.race_application_query_service.application.model.Application;
import com.trail_race.race_application_query_service.application.repository.ApplicationRepository;
import com.trail_race.race_application_query_service.exception.dao.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ApplicationService {

    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;

    public ApplicationResponse getApplicationById(String applicationId) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new NotFoundException("Application with id: " + applicationId + " not found"));
        return applicationMapper.modelToResponse(application);
    }

    public ApplicationsResponse findAll() {
        List<Application> applications = applicationRepository.findAll();
        return ApplicationsResponse.builder()
                .applications(applicationMapper.modelToResponseList(applications))
                .build();
    }

    public void create(ApplicationRequest applicationRequest) {
        Application application = applicationMapper.requestToModel(applicationRequest);
        applicationRepository.save(application);
    }

    public void delete(String id) {
        applicationRepository.deleteById(id);
    }

    public void patch(ApplicationRequest applicationRequest) {
        Application application = applicationRepository.findById(applicationRequest.getId()).orElse(null);
        if (application == null) {
            log.error("Cannot patch application with id: " + applicationRequest.getId() + " because it was not found");
            return;
        }
        Application updatedApplication = applicationMapper.patchModel(application, applicationRequest);
        applicationRepository.save(updatedApplication);
    }
}
