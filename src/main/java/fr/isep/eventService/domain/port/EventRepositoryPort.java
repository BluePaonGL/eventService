package fr.isep.eventService.domain.port;

import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.model.EventParticipant;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;

import java.util.List;

public interface EventRepositoryPort {

    Event findByEventId(String eventId);
    Event save(Event event);

    List<Event> findAll();

    void deleteEvent(String eventId);

    EventParticipantDAO save(EventParticipant eventParticipant);


}
