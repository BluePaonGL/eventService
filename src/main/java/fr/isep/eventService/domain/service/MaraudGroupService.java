package fr.isep.eventService.domain.service;

import fr.isep.eventService.application.DTO.MaraudGroupDto;
import fr.isep.eventService.application.DTO.MaraudGroupMemberDto;
import fr.isep.eventService.application.port.MaraudGroupServicePort;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.domain.port.MaraudGroupRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupMemberDAO;
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
    public MaraudGroup getMaraudGroupById(String maraudGroupId) {
        MaraudGroup maraudGroup = this.maraudGroupRepositoryPort.findByMaraudGroupId(maraudGroupId);
        if(maraudGroup != null){
            maraudGroup.setMaraudGroupMembers(this.maraudGroupRepositoryPort.getAllMemberByMaraudGroupId(maraudGroupId));
        }
        return maraudGroup;
    }

    @Override
    public List<MaraudGroup> getListOfMaraudGroupByEventId(String eventId) {
        List<MaraudGroup> result = this.maraudGroupRepositoryPort.getAllMaraudGroupsByEventId(eventId);
        for (MaraudGroup maraudGroup : result) {
            maraudGroup.setMaraudGroupMembers(this.maraudGroupRepositoryPort.getAllMemberByMaraudGroupId(maraudGroup.getMaraudGroupId()));
        }
        return result;
    }

    @Override
    public List<MaraudGroup> getAllMaraudGroupsByMemberId(String memberId) {
        List<MaraudGroup> result = this.maraudGroupRepositoryPort.getAllMaraudGroupsByMemberId(memberId);
        for (MaraudGroup maraudGroup : result) {
            maraudGroup.setMaraudGroupMembers(this.maraudGroupRepositoryPort.getAllMemberByMaraudGroupId(maraudGroup.getMaraudGroupId()));
        }
        return result;
    }

    @Override
    public MaraudGroup saveMaraudGroup(MaraudGroupDto maraudGroupDto) {
        MaraudGroup maraudGroup = modelMapper.map(maraudGroupDto, MaraudGroup.class);
        return this.maraudGroupRepositoryPort.save(maraudGroup);
    }

    @Override
    public void deleteMaraudGroup(String maraudGroupId) {
        this.maraudGroupRepositoryPort.deleteMaraudGroup(maraudGroupId);
    }

    @Override
    public List<MaraudGroup> getMaraudGroups() {
        List<MaraudGroup> result = this.maraudGroupRepositoryPort.findAll();
        for (MaraudGroup maraudGroup : result) {
            maraudGroup.setMaraudGroupMembers(this.maraudGroupRepositoryPort.getAllMemberByMaraudGroupId(maraudGroup.getMaraudGroupId()));
        }
        return result;
    }

    @Override
    public MaraudGroupMemberDAO saveMaraudGroupMember(MaraudGroupMemberDto maraudGroupMemberDto) {
        return this.maraudGroupRepositoryPort.save(maraudGroupMemberDto);
    }

    @Override
    public void deleteMaraudGroupMember(String participantId, String maraudGroupId) {
        this.maraudGroupRepositoryPort.deleteMaraudGroupMember(participantId, maraudGroupId);
    }
}
