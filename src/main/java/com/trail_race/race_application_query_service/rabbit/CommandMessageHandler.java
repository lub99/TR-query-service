package com.trail_race.race_application_query_service.rabbit;

import com.trail_race.race_application_query_service.application.dto.ApplicationRequest;
import com.trail_race.race_application_query_service.application.service.ApplicationService;
import com.trail_race.race_application_query_service.rabbit.data.CommandMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommandMessageHandler {

    private final ApplicationService applicationService;

    public void handleMessage(CommandMessage commandMessage) {
        log.debug("Handling command message:" + commandMessage);

        if (commandMessage.getCommandType() == null) {
            throw new IllegalArgumentException("CommandType is null");
        }

        ApplicationRequest applicationRequest = mapToRequest(commandMessage);
        switch (commandMessage.getCommandType()) {
            case CREATE -> applicationService.create(applicationRequest);
            case DELETE -> applicationService.delete(applicationRequest.getId());
            case PATCH -> applicationService.patch(applicationRequest);
            default ->
                    throw new IllegalArgumentException("Unsupported CommandType: " + commandMessage.getCommandType());
        }
    }

    private ApplicationRequest mapToRequest(CommandMessage commandMessage) {
        return ApplicationRequest.builder()
                .id(commandMessage.getId())
                .firstName(commandMessage.getFirstName())
                .lastName(commandMessage.getLastName())
                .club(commandMessage.getClub())
                .distance(commandMessage.getDistance())
                .build();
    }
}
