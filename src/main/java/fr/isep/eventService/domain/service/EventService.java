package fr.isep.eventService.domain.service;

import fr.isep.eventService.application.DTO.EventDto;
import fr.isep.eventService.application.DTO.EventParticipantDto;
import fr.isep.eventService.application.port.EventServicePort;
import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.port.EventRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService implements EventServicePort {

    private final EventRepositoryPort eventRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public Event saveEvent(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        return this.eventRepositoryPort.save(event);
    }

    @Override
    public Event getEvent(String eventId) {
        Event event = this.eventRepositoryPort.findByEventId(eventId);
        if(event != null){
            event.setParticipantsId(this.eventRepositoryPort.getAllParticipantByEventId(eventId));
        }
        return event;
    }

    @Override
    public List<Event> getEvents() {
        List<Event> result = this.eventRepositoryPort.findAll();
        for (Event event : result) {
            event.setParticipantsId(this.eventRepositoryPort.getAllParticipantByEventId(event.getEventId()));
        }
        return result;
    }

    @Override
    public void deleteEvent(String eventId) {
        this.eventRepositoryPort.deleteEvent(eventId);
    }

    @Override
    public EventParticipantDAO saveEventParticipant(EventParticipantDto eventParticipantDto) {
        return this.eventRepositoryPort.save(eventParticipantDto);
    }


    @Override
    public List<Event> getEventsByParticipantId(String participantId) {
        List<Event> result = this.eventRepositoryPort.getAllEventsByParticipantId(participantId);
        for (Event event : result) {
            event.setParticipantsId(this.eventRepositoryPort.getAllParticipantByEventId(event.getEventId()));
        }
        return result;
    }

    @Override
    public void deleteParticipant(String eventId, String participantId) {
        this.eventRepositoryPort.deleteEventParticipant(eventId, participantId);
    }

    @Override
    public List<String> getParticipantsNotInMaraudGroupForEventId(String eventId) {
        return this.eventRepositoryPort.getParticipantsNotInMaraudGroupsForEventId(eventId);
    }

}
