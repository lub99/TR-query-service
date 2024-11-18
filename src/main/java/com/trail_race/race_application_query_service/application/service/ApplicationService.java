package com.trail_race.race_application_query_service.application.service;

import com.trail_race.race_application_query_service.application.dto.ApplicationResponse;
import com.trail_race.race_application_query_service.application.dto.ApplicationsResponse;
import com.trail_race.race_application_query_service.application.mapper.ApplicationMapper;
import com.trail_race.race_application_query_service.application.model.Application;
import com.trail_race.race_application_query_service.application.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
}
