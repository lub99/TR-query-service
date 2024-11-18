package com.trail_race.race_application_query_service.application.mapper;

import com.trail_race.race_application_query_service.application.dto.ApplicationRequest;
import com.trail_race.race_application_query_service.application.dto.ApplicationResponse;
import com.trail_race.race_application_query_service.application.model.Application;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", imports = Objects.class)
@Component
public abstract class ApplicationMapper {

    public abstract ApplicationResponse modelToResponse(Application application);

    public abstract List<ApplicationResponse> modelToResponseList(List<Application> applications);

    public abstract Application requestToModel(ApplicationRequest applicationRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Application patchModel(@MappingTarget Application application,
                                           ApplicationRequest applicationRequest);
}
