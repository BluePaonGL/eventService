package fr.isep.eventService.infrastructure.adapter_repository_db.repository;

import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MaraudGroupRepository extends JpaRepository<MaraudGroupDao, Long>, JpaSpecificationExecutor<MaraudGroupDao> {
    MaraudGroupDao findByGroupId(String groupId);
    MaraudGroupDao findByEventId(String eventId);
    MaraudGroupDao findByGroupLabel(String groupLabel);
    List<MaraudGroupDao> findAllByUsers_UserId(String userId);

}
