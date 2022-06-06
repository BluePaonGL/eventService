package fr.isep.eventService.domain.port;

import fr.isep.eventService.domain.criteria.MaraudGroupCriteria;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MaraudGroupRepositoryPort {
    MaraudGroup findById(String groupId);
    List<MaraudGroup> findByEventId(String eventId);
    MaraudGroup findByGroupLabel(String groupLabel);
    List<MaraudGroup> getListOfMaraudGroupByUserIdIn(String userId);

    Page<MaraudGroup> pageMaraudGroup(MaraudGroupCriteria maraudGroupCriteria);

    MaraudGroup saveMaraudGroup(MaraudGroup maraudGroup);
    MaraudGroup addUserToMaraudGroup(MaraudGroup maraudGroup, String userId);
    MaraudGroup removeUserFromMaraudGroup(MaraudGroup maraudGroup, String userId);
    List<MaraudGroup> findAll();

    List<String> getAllUserInGroup(String groupId);

    void delete(String groupId);
}
