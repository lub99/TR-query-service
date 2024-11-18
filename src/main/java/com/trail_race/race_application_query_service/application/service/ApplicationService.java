package com.trail_race.race_application_query_service.application.service;

import com.trail_race.race_application_query_service.application.dto.ApplicationRequest;
import com.trail_race.race_application_query_service.application.dto.ApplicationResponse;
import com.trail_race.race_application_query_service.application.dto.ApplicationsResponse;
import com.trail_race.race_application_query_service.application.mapper.ApplicationMapper;
import com.trail_race.race_application_query_service.application.model.Application;
import com.trail_race.race_application_query_service.application.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final ApplicationMapper applicationMapper;
    private final ApplicationRepository applicationRepository;

    public ApplicationResponse getApplicationById(String applicationId) {
        // todo throw exception here
        Application application = applicationRepository.findById(applicationId).orElse(null);
        return applicationMapper.modelToResponse(application);
    }

    public ApplicationsResponse findAll() {
        List<Application> applications = applicationRepository.findAll();
        return ApplicationsResponse.builder()
                .applications(applicationMapper.modelToResponseList(applications))
                .build();
    }

    public void create(ApplicationRequest applicationRequest) {
        validateCreateRequest(applicationRequest);
        Application application = applicationMapper.requestToModel(applicationRequest);
        applicationRepository.save(application);
    }

    public void delete(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        applicationRepository.deleteById(id);
    }

    public void patch(ApplicationRequest applicationRequest) {
        if (applicationRequest.getId() == null) {
            throw new IllegalArgumentException("Id can't be null");
        }
        // todo throw exception
        Application application = applicationRepository.findById(applicationRequest.getId()).orElseThrow();
        Application updatedApplication = applicationMapper.patchModel(application, applicationRequest);
        applicationRepository.save(updatedApplication);
    }

    private void validateCreateRequest(ApplicationRequest applicationRequest) {
        if (applicationRequest.getId() == null) {
            throw new IllegalArgumentException("Id is null");
        }
        if (applicationRequest.getLastName() == null) {
            throw new IllegalArgumentException("LastName is null");
        }
        if (applicationRequest.getFirstName() == null) {
            throw new IllegalArgumentException("FirstName is null");
        }
        if (applicationRequest.getDistance() == null) {
            throw new IllegalArgumentException("Distance is null");
        }
    }
}
