package fr.isep.eventService.application.port;

import fr.isep.eventService.application.DTO.EventDto;
import fr.isep.eventService.application.DTO.EventParticipantDto;
import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;

import java.util.List;

public interface EventServicePort {
    Event saveEvent(EventDto eventDto);

    Event getEvent(String eventId);

    List<Event> getEvents();

    void deleteEvent(String eventId);

    EventParticipantDAO saveEventParticipant(EventParticipantDto eventParticipantDto);

    List<Event> getEventsByParticipantId(String participantId);

    void deleteParticipant(String eventId, String participantId);

    List<String> getParticipantsNotInMaraudGroupForEventId(String eventId);
}
