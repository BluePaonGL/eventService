package fr.isep.eventService.infrastructure.adapter_repository_db.adapter;

import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.port.EventRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Component
public class EventRepositoryAdapter implements EventRepositoryPort {

    private EventRepository eventRepository;

    private final ModelMapper modelMapper;

    @Override
    public Event findByEventId(String eventId) {
        EventDAO eventDAOOptional = this.eventRepository.findByEventId(eventId);
        try {
            return modelMapper.map(eventDAOOptional, Event.class);
        } catch (NoSuchElementException exception) {
            throw new NoSuchElementException("This event does not exist in the database", exception);
        }
    }

    @Override
    public Event save(Event event) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        return null;
    }
}
