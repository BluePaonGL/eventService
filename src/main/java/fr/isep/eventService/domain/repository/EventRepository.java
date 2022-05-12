package fr.isep.eventService.domain.repository;

import fr.isep.eventService.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>{
}
