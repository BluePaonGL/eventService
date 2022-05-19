package fr.isep.eventService.infrastructure.adapter_repository_db.repository;

import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaraudGroupRepository extends JpaRepository<MaraudGroupDao, Long>{
    MaraudGroupDao findByGroupId(String groupId);
}
