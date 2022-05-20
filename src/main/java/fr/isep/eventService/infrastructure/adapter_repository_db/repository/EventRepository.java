package fr.isep.eventService.infrastructure.adapter_repository_db.repository;

import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventDAO, Long>{
    EventDAO findByEventId(String eventId);

    List<EventDAO> findByEventParticipantDAOs_ParticipantId(String participantId);






}
