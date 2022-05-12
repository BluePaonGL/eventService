package fr.isep.eventService.application.service;

import fr.isep.eventService.application.DTO.EventDto;
import fr.isep.eventService.application.port.EventServicePort;
import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventService implements EventServicePort {

    private final EventRepository eventRepository;
    //private final Keycloak keycloak;
    private final ModelMapper modelMapper;

    @Override
    public Event saveEvent(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);
        this.eventRepository.save(event);
        return event;
    }
    @Override
    public Optional<Event> getEvent(Long eventId) {
        return eventRepository.findById(eventId);
    }

    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }
}
