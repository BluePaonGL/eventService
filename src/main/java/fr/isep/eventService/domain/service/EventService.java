package fr.isep.eventService.domain.service;

import fr.isep.eventService.application.DTO.EventDto;
import fr.isep.eventService.application.port.EventServicePort;
import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.port.EventRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService implements EventServicePort {

    private final EventRepositoryPort eventRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public Event saveEvent(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        this.eventRepositoryPort.save(event);
        return event;
    }
    @Override
    public Event getEvent(String eventId) {
        return eventRepositoryPort.findByEventId(eventId);
    }

    @Override
    public List<Event> getEvents() {
        return eventRepositoryPort.findAll();
    }

}
