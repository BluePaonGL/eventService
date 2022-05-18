package fr.isep.eventService.infrastructure.adapter_repository_db.repository;

import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.model.EventParticipant;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<EventDAO, Long>{
    EventDAO findByEventId(String eventId);

    EventParticipantDAO save(EventParticipant eventParticipant);
}
