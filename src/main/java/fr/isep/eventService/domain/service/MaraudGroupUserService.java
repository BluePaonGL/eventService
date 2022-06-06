package fr.isep.eventService.domain.service;

import fr.isep.eventService.application.DTO.MaraudGroupUserDto;
import fr.isep.eventService.application.port.MaraudGroupUserServicePort;
import fr.isep.eventService.domain.model.MaraudGroupUser;
import fr.isep.eventService.domain.port.EventRepositoryPort;
import fr.isep.eventService.domain.port.MaraudGroupUserRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaraudGroupUserService implements MaraudGroupUserServicePort {

    private final MaraudGroupUserRepositoryPort maraudGroupUserRepositoryPort;
    private final EventRepositoryPort eventRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public MaraudGroupUser getMaraudGroupByUserId(String userId){
        return this.maraudGroupUserRepositoryPort.findById(userId);
    }

    @Override
    public MaraudGroupUser saveMaraudGroupUser(MaraudGroupUserDto maraudGroupUserDto){
        MaraudGroupUser maraudGroupUser = this.modelMapper.map(maraudGroupUserDto, MaraudGroupUser.class);
        return this.maraudGroupUserRepositoryPort.saveMaraudGroupUser(maraudGroupUser);
    }

    @Override
    public List<MaraudGroupUser> getMaraudGroupUsers(){
        return this.maraudGroupUserRepositoryPort.findAll();
    }

    //TODO Lister tous participants d'un event -> Récup dans une liste les ID -> Enlever les ID déjà liés à un groupe de l'évent

    @Override
    public List<MaraudGroupUser> getAllUsersWithNoGroupForEvent(String eventId){
        List<String> participantsIdList = this.eventRepositoryPort.getAllParticipantByEventId(eventId);
        return this.maraudGroupUserRepositoryPort.getAllUsersWithNoGroupForEvent(eventId, participantsIdList);
    }
}
