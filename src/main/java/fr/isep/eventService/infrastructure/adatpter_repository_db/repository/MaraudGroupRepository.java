package fr.isep.eventService.infrastructure.adatpter_repository_db.repository;

import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.infrastructure.adatpter_repository_db.DAO.MaraudGroupDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaraudGroupRepository extends JpaRepository<MaraudGroupDao, Long>{
    MaraudGroupDao findByGroupId(String groupId);
    MaraudGroupDao findByEventId(String eventId);
    MaraudGroupDao findByGroupLabel(String groupLabel);
    List<MaraudGroupDao> findMaraudGroupDaoByListOfUsersIn(String userId);

}
