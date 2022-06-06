package fr.isep.eventService.infrastructure.adapter_repository_db.repository;

import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupMemberDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaraudGroupMemberRepository extends JpaRepository<MaraudGroupMemberDAO, Long> {
    List<MaraudGroupMemberDAO> getMaraudGroupMemberDAOSByMaraudGroupId(String maraudGroupId);

    List<MaraudGroupMemberDAO> getMaraudGroupMemberDAOSByMemberId(String memberId);

    MaraudGroupMemberDAO findByMaraudGroupIdAndMemberId(String maraudGroupId, String memberId);

}
