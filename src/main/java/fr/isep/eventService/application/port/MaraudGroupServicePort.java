package fr.isep.eventService.application.port;

import fr.isep.eventService.application.DTO.MaraudGroupDto;
import fr.isep.eventService.application.DTO.MaraudGroupMemberDto;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupMemberDAO;

import java.util.List;

public interface MaraudGroupServicePort {
    MaraudGroup getMaraudGroupById(String maraudGroupId);

    List<MaraudGroup> getListOfMaraudGroupByEventId(String eventId);

    List<MaraudGroup> getAllMaraudGroupsByMemberId(String memberId);

    MaraudGroup saveMaraudGroup(MaraudGroupDto maraudGroupDto);

    void deleteMaraudGroup(String maraudGroupId);

    List<MaraudGroup> getMaraudGroups();

    MaraudGroupMemberDAO saveMaraudGroupMember(MaraudGroupMemberDto maraudGroupMemberDto);

    void deleteMaraudGroupMember(String participantId, String maraudGroupId);

}
