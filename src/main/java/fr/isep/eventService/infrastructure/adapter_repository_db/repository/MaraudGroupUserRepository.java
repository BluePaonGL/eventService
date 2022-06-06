package fr.isep.eventService.infrastructure.adapter_repository_db.repository;

import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupUserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface MaraudGroupUserRepository extends JpaRepository<MaraudGroupUserDao, Long>, JpaSpecificationExecutor<MaraudGroupUserDao> {
    MaraudGroupUserDao findByUserId(String userId);

    Set<MaraudGroupUserDao> findByGroupId(String groupId);

}
