package com.trail_race.race_application_query_service.application.mapper;

import com.trail_race.race_application_query_service.application.dto.ApplicationResponse;
import com.trail_race.race_application_query_service.application.model.Application;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ApplicationMapper {

    public abstract ApplicationResponse modelToResponse(Application application);
    public abstract List<ApplicationResponse> modelToResponseList(List<Application> applications);
}
