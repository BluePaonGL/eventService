package fr.isep.eventService.domain.port;

import fr.isep.eventService.domain.model.MaraudGroupUser;

import java.util.List;

public interface MaraudGroupUserRepositoryPort {
    MaraudGroupUser findByUserId(String userId);
    MaraudGroupUser saveMaraudGroupUser(MaraudGroupUser maraudGroupUser);
    List<MaraudGroupUser> findAll();

    List<MaraudGroupUser> getAllUsersWithNoGroupForEvent(String eventId, List<String> participantsIdList);
    void delete(String userId);
}
