package fr.isep.eventService.infrastructure.adapter_repository_db.repository;

import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MaraudGroupRepository extends JpaRepository<MaraudGroupDao, Long>, JpaSpecificationExecutor<MaraudGroupDao> {
    MaraudGroupDao findByMaraudGroupId(String maraudGroupId);

    List<MaraudGroupDao> findAllByEventId(String eventId);

}
