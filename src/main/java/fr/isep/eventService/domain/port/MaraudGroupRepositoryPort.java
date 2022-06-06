package fr.isep.eventService.domain.port;

import fr.isep.eventService.application.DTO.MaraudGroupDto;
import fr.isep.eventService.application.DTO.MaraudGroupMemberDto;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupMemberDAO;

import java.util.List;

public interface MaraudGroupRepositoryPort {
    MaraudGroup save(MaraudGroup maraudGroup);

    List<MaraudGroup> findAll();

    void deleteMaraudGroup(String maraudGroupId);

    MaraudGroup findByMaraudGroupId(String maraudGroupId);

    List<MaraudGroup> getAllMaraudGroupsByEventId(String eventId);

    List<MaraudGroup> getAllMaraudGroupsByMemberId(String memberId);

    MaraudGroupMemberDAO save(MaraudGroupMemberDto maraudGroupMemberDto);

    List<String> getAllMemberByMaraudGroupId(String maraudGroupId);

    void deleteMaraudGroupMember(String participantId, String maraudGroupId);

}
