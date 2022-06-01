package fr.isep.eventService.domain.port;

import fr.isep.eventService.domain.model.MaraudGroupUser;
import fr.isep.eventService.infrastructure.adatpter_repository_db.DAO.MaraudGroupUserDao;

import java.util.List;

public interface MaraudGroupUserRepositoryPort {
    MaraudGroupUser findById(String userId);
    MaraudGroupUser saveMaraudGroupUser(MaraudGroupUser maraudGroupUser);
    List<MaraudGroupUser> findAll();
    void delete(String userId);
}
