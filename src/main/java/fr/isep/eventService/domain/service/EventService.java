package fr.isep.eventService.domain.service;

import fr.isep.eventService.application.DTO.EventDto;
import fr.isep.eventService.application.DTO.EventParticipantDto;
import fr.isep.eventService.application.port.EventServicePort;
import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.port.EventRepositoryPort;
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
        event.setParticipantsId(this.eventRepositoryPort.getAllParticipantByEventId(eventId));

        return event;
    }

    @Override
    public List<Event> getEvents() {
        return this.eventRepositoryPort.findAll();
    }

    @Override
    public void deleteEvent(String eventId) {
        this.eventRepositoryPort.deleteEvent(eventId);
    }

    @Override
    public EventParticipantDAO saveEventParticipant(EventParticipantDto eventParticipantDto) {

        return this.eventRepositoryPort.save(eventParticipantDto);
    }

}
