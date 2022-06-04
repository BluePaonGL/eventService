package fr.isep.eventService.application.port;

import fr.isep.eventService.application.DTO.MaraudGroupUserDto;
import fr.isep.eventService.domain.model.MaraudGroupUser;

import java.util.List;

public interface MaraudGroupUserServicePort {
    MaraudGroupUser getMaraudGroupUserById(String userId);
    MaraudGroupUser saveMaraudGroupUser(MaraudGroupUserDto maraudGroupUserDto);
    List<MaraudGroupUser> getMaraudGroupUsers();
}
