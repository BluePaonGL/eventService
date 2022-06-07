package fr.isep.eventService.infrastructure.adapter_repository_db.repository;

import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface EventParticipantRepository extends JpaRepository<EventParticipantDAO, Long> {
    List<EventParticipantDAO> getEventParticipantDAOSByEventId(String eventId);

    List<EventParticipantDAO> getEventParticipantDAOSByParticipantId(String participantId);

    EventParticipantDAO findByEventIdAndParticipantId(String eventId, String ParticipantId);

    List<EventParticipantDAO> findEventParticipantDAOSByEventIdAndParticipantIdNotIn(String eventId, Collection<String> participantIds);

    List<EventParticipantDAO> findByEventIdIsAndParticipantIdNotIn(String eventId, Collection<String> participantIds);
}
