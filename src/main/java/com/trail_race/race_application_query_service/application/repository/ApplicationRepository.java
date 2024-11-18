package com.trail_race.race_application_query_service.application.repository;

import com.trail_race.race_application_query_service.application.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, String> {
}
