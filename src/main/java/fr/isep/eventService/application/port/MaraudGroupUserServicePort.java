package fr.isep.eventService.application.port;

import fr.isep.eventService.application.DTO.MaraudGroupUserDto;
import fr.isep.eventService.domain.model.MaraudGroupUser;

import java.util.List;

public interface MaraudGroupUserServicePort {
    MaraudGroupUser getMaraudGroupByUserId(String userId);
    MaraudGroupUser saveMaraudGroupUser(MaraudGroupUserDto maraudGroupUserDto);
    List<MaraudGroupUser> getMaraudGroupUsers();
    List<MaraudGroupUser> getAllUsersWithNoGroupForEvent(String eventId);
}
