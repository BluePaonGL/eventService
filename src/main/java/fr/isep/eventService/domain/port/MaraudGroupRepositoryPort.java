package fr.isep.eventService.domain.port;

import fr.isep.eventService.domain.criteria.MaraudGroupCriteria;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.infrastructure.adatpter_repository_db.DAO.MaraudGroupDao;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaraudGroupRepositoryPort {
    MaraudGroup findById(String groupId);
    MaraudGroup findByEventId(String eventId);
    MaraudGroup findByGroupLabel(String groupLabel);

    Page<MaraudGroup> pageMaraudGroup(MaraudGroupCriteria maraudGroupCriteria);
    MaraudGroupDao saveMaraudGroup(MaraudGroupDao maraudGroupDao);
    //Add user to group ?
    List<MaraudGroup> findAll();

    void delete(String groupId);
}
