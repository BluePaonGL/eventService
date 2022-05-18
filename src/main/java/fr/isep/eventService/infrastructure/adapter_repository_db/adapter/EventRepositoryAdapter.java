package fr.isep.eventService.infrastructure.adapter_repository_db.adapter;

import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.port.EventRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        EventDAO eventDAO = modelMapper.map(event, EventDAO.class);
        return modelMapper.map(this.eventRepository.save(eventDAO), Event.class);
    }

    @Override
    public List<Event> findAll() {
        List<EventDAO> listDAO = this.eventRepository.findAll();
        List<Event> result = new ArrayList<>();
        for (int i = 0; i < listDAO.size(); i++) {
            result.add(modelMapper.map(listDAO.get(i), Event.class));
        }
        return result;
    }

    @Override
    public void deleteEvent(String eventId) {
        this.eventRepository.delete(this.eventRepository.findByEventId(eventId));
    }
}
