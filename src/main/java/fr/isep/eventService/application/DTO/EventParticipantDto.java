package fr.isep.eventService.application.DTO;

import fr.isep.eventService.domain.model.Event;
import lombok.*;

@Data
@Getter
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
public class EventParticipantDto {

    private String participantId;

    private Event event;
}
