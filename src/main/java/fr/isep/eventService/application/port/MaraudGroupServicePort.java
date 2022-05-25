package fr.isep.eventService.application.port;

import fr.isep.eventService.application.DTO.MaraudGroupDto;
import fr.isep.eventService.domain.model.MaraudGroup;

import java.util.List;

public interface MaraudGroupServicePort {
    MaraudGroup getMaraudGroupById(String groupId);
    MaraudGroup getMaraudGroupByEventId(String eventId);
    MaraudGroup getMaraudGroupByGroupLabel(String groupLabel);

    List<MaraudGroup> getListOfMaraudGroupByUserIdIn(String userId);

    MaraudGroup saveMaraudGroup(MaraudGroupDto maraudGroupDto);

    List<MaraudGroup> getMaraudsGroups();

    MaraudGroup addUserToMaraudGroup(String groupId, String userId);

    //TODO page maraudGroup ?
}
