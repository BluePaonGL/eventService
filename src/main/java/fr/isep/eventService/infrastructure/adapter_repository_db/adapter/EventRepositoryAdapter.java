package fr.isep.eventService.infrastructure.adapter_repository_db.adapter;

import fr.isep.eventService.application.DTO.EventParticipantDto;
import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.port.EventRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupMemberDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.EventParticipantRepository;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.EventRepository;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.MaraudGroupMemberRepository;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.MaraudGroupRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EventRepositoryAdapter implements EventRepositoryPort {

    private EventRepository eventRepository;
    private final EventParticipantRepository eventParticipantRepository;

    private final MaraudGroupRepository maraudGroupRepository;

    private final MaraudGroupMemberRepository maraudGroupMemberRepository;
    private final ModelMapper modelMapper;

    @Override
    public Event findByEventId(String eventId) {
        EventDAO eventDAOOptional = this.eventRepository.findByEventId(eventId);
        if(eventDAOOptional != null){
            try {
                return modelMapper.map(eventDAOOptional, Event.class);
            } catch (NoSuchElementException exception) {
                throw new NoSuchElementException("This event does not exist in the database", exception);
            }
        }
        return null;
    }

    @Override
    public Event save(Event event) {
        EventDAO eventDAO = modelMapper.map(event, EventDAO.class);
        return modelMapper.map(this.eventRepository.save(eventDAO), Event.class);
    }

    @Override
    public List<Event> findAll() {
        List<EventDAO> listDAO = this.eventRepository.findAll(Sort.by(Sort.Direction.ASC, "date").and(Sort.by(Sort.Direction.ASC, "startingTime")));

        return listDAO.stream().map(event -> modelMapper.map(event, Event.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteEvent(String eventId) {
        this.eventRepository.delete(this.eventRepository.findByEventId(eventId));
        List<String> eventParticipantsList = this.getAllParticipantByEventId(eventId);
        for (String eventParticipantId : eventParticipantsList){
            this.eventParticipantRepository.delete(this.eventParticipantRepository.findByEventIdAndParticipantId(eventId, eventParticipantId));
        }
    }

    @Override
    public EventParticipantDAO save(EventParticipantDto eventParticipant) {
        EventParticipantDAO eventParticipantDAO = EventParticipantDAO.builder()
                .eventId(eventParticipant.getEventId())
                .participantId(eventParticipant.getParticipantId())
                .build();
        return this.eventParticipantRepository.save(eventParticipantDAO);
    }

    public List<String> getAllParticipantByEventId(String eventId) {
        return this.eventParticipantRepository.getEventParticipantDAOSByEventId(eventId)
                .stream().map(EventParticipantDAO::getParticipantId)
                .collect(Collectors.toList());
    }

    public List<Event> getAllEventsByParticipantId(String eventParticipantId) {

        List<String> eventIdList = this.eventParticipantRepository.getEventParticipantDAOSByParticipantId(eventParticipantId)
                .stream().map(EventParticipantDAO::getEventId)
                .collect(Collectors.toList());
        List<Event> result = new ArrayList<>();
        for (String eventId : eventIdList) {
            result.add(this.modelMapper.map(this.eventRepository.findByEventId(eventId), Event.class));
        }
        return result;
    }

    public void deleteEventParticipant(String eventId, String participantId) {
        this.eventParticipantRepository.delete(this.eventParticipantRepository.findByEventIdAndParticipantId(eventId, participantId));
    }

    @Override
    public List<EventParticipantDAO> getParticipantsNotInMaraudGroupsForEventId(String eventId) {
        List<MaraudGroupDao> maraudGroupDaos = this.maraudGroupRepository.findAllByEventId(eventId);
        List<String> participantsInMaraudGroups = new ArrayList<>();

        for (MaraudGroupDao maraudGroupDao : maraudGroupDaos) {
            List<MaraudGroupMemberDAO> maraudGroupMemberDAOS = this.maraudGroupMemberRepository.getMaraudGroupMemberDAOSByMaraudGroupId(maraudGroupDao.getMaraudGroupId());
            for (MaraudGroupMemberDAO maraudGroupMemberDAO : maraudGroupMemberDAOS) {
                participantsInMaraudGroups.add(maraudGroupMemberDAO.getMemberId());
            }
        }
        return this.eventParticipantRepository.findByEventIdIsAndParticipantIdNotIn(eventId, participantsInMaraudGroups);
    }
}
