package com.trail_race.race_application_query_service.application.dto;

import lombok.Data;

@Data
public class ApplicationResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String club;
    private String distance;
}
