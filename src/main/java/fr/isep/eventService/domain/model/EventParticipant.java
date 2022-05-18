package fr.isep.eventService.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EventParticipant {
    private String eventParticipantId;
    private String participant_id;
    private Event event;

    public EventParticipant(String participantId, Event event) {
        this.participant_id = participantId;
        this.event = event;
    }
}
