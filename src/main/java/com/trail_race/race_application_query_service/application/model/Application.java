package com.trail_race.race_application_query_service.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Application {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String club;

    @Enumerated(EnumType.STRING)
    private Distance distance;
}
