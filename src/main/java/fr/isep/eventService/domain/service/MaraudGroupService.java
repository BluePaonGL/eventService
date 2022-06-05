package fr.isep.eventService.domain.service;

import fr.isep.eventService.application.DTO.MaraudGroupDto;
import fr.isep.eventService.application.port.MaraudGroupServicePort;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.domain.port.MaraudGroupRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MaraudGroupService implements MaraudGroupServicePort {
    private final MaraudGroupRepositoryPort maraudGroupRepositoryPort;
    private final ModelMapper modelMapper;

    @Override
    public MaraudGroup getMaraudGroupById(String groupId){
        return this.maraudGroupRepositoryPort.findById(groupId);
    }

    @Override
    public List<MaraudGroup> getListOfMaraudGroupByEventId(String eventId){
        return this.maraudGroupRepositoryPort.findByEventId(eventId);
    }

    @Override
    public MaraudGroup getMaraudGroupByGroupLabel(String groupLabel){
        return this.maraudGroupRepositoryPort.findByGroupLabel(groupLabel);
    }

    @Override
    public List<MaraudGroup> getListOfMaraudGroupByUserIdIn(String userId){
        return this.maraudGroupRepositoryPort.getListOfMaraudGroupByUserIdIn(userId);
    }

    @Override
    public MaraudGroup saveMaraudGroup(MaraudGroupDto maraudGroupDto){
        MaraudGroup maraudGroup = this.modelMapper.map(maraudGroupDto, MaraudGroup.class);
        return this.maraudGroupRepositoryPort.saveMaraudGroup(maraudGroup);
    }

    @Override
    public List<MaraudGroup> getMaraudsGroups(){
        return this.maraudGroupRepositoryPort.findAll();
    }

    @Override
    public MaraudGroup addUserToMaraudGroup(String groupId, String userId){
        MaraudGroup maraudGroup = this.maraudGroupRepositoryPort.findById(groupId);
        return this.maraudGroupRepositoryPort.addUserToMaraudGroup(maraudGroup, userId);
    }

    @Override
    public MaraudGroup removeUserFromMaraudGroup(String groupId, String userId){
        MaraudGroup maraudGroup = this.maraudGroupRepositoryPort.findById(groupId);
        return this.maraudGroupRepositoryPort.removeUserFromMaraudGroup(maraudGroup, userId);
    }
}
