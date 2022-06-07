package fr.isep.eventService.infrastructure.adapter_repository_db.adapter;

import fr.isep.eventService.application.DTO.MaraudGroupMemberDto;
import fr.isep.eventService.domain.criteria.MaraudGroupCriteria;
import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.domain.port.MaraudGroupRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupMemberDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.MaraudGroupMemberRepository;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.MaraudGroupRepository;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MaraudGroupRepositoryAdapter implements MaraudGroupRepositoryPort {

    private MaraudGroupRepository maraudGroupRepository;

    private MaraudGroupMemberRepository maraudGroupMemberRepository;
    private final ModelMapper modelMapper;


    @Override
    public MaraudGroup save(MaraudGroup maraudGroup) {
        MaraudGroupDao maraudGroupDao = modelMapper.map(maraudGroup, MaraudGroupDao.class);
        return modelMapper.map(this.maraudGroupRepository.save(maraudGroupDao), MaraudGroup.class);
    }

    @Override
    public List<MaraudGroup> findAll() {
        List<MaraudGroupDao> listDAO = this.maraudGroupRepository.findAll();
        return listDAO.stream().map(maraudGroup -> modelMapper.map(maraudGroup, MaraudGroup.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteMaraudGroup(String maraudGroupId) {
        this.maraudGroupRepository.delete(this.maraudGroupRepository.findByMaraudGroupId(maraudGroupId));
        List<MaraudGroupMemberDAO> maraudGroupMemberDAOList = this.maraudGroupMemberRepository.findAllByMaraudGroupId(maraudGroupId);
        for(MaraudGroupMemberDAO maraudGroupMemberDAO : maraudGroupMemberDAOList){
            this.maraudGroupMemberRepository.delete(this.maraudGroupMemberRepository.findByMaraudGroupIdAndMemberId(maraudGroupId, maraudGroupMemberDAO.getMemberId()));
        }
    }

    @Override
    public MaraudGroup findByMaraudGroupId(String maraudGroupId) {
        MaraudGroupDao maraudGroupDao = this.maraudGroupRepository.findByMaraudGroupId(maraudGroupId);
        if(maraudGroupDao != null){
            try {
                return modelMapper.map(maraudGroupDao, MaraudGroup.class);
            } catch (NoSuchElementException exception) {
                throw new NoSuchElementException("This maraud group does not exist in the database", exception);
            }
        }
        return null;
    }

    @Override
    public List<MaraudGroup> getAllMaraudGroupsByEventId(String eventId) {
        List<MaraudGroupDao> listDAO = this.maraudGroupRepository.findAllByEventId(eventId);
        return listDAO.stream().map(maraudGroup -> modelMapper.map(maraudGroup, MaraudGroup.class)).collect(Collectors.toList());
    }

    @Override
    public List<MaraudGroup> getAllMaraudGroupsByMemberId(String memberId) {
        List<MaraudGroupMemberDAO> maraudGroupMemberDAOList = this.maraudGroupMemberRepository.getMaraudGroupMemberDAOSByMemberId(memberId);
        List<MaraudGroup> result = new ArrayList<>();
        if(maraudGroupMemberDAOList.size()!=0){
            List<String> maraudGroupIdList = maraudGroupMemberDAOList
                    .stream().map(MaraudGroupMemberDAO::getMaraudGroupId)
                    .collect(Collectors.toList());
            for (String maraudGroupId : maraudGroupIdList) {
                result.add(this.modelMapper.map(this.maraudGroupRepository.findByMaraudGroupId(maraudGroupId), MaraudGroup.class));
            }
        }

        return result;
    }

    @Override
    public MaraudGroupMemberDAO save(MaraudGroupMemberDto maraudGroupMemberDto) {
        MaraudGroupMemberDAO maraudGroupMemberDAO = MaraudGroupMemberDAO.builder()
                .maraudGroupId(maraudGroupMemberDto.getMaraudGroupId())
                .memberId(maraudGroupMemberDto.getParticipantId())
                .build();
        return this.maraudGroupMemberRepository.save(maraudGroupMemberDAO);
    }

    @Override
    public List<String> getAllMemberByMaraudGroupId(String maraudGroupId) {
        return this.maraudGroupMemberRepository.getMaraudGroupMemberDAOSByMaraudGroupId(maraudGroupId)
                .stream().map(MaraudGroupMemberDAO::getMemberId)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMaraudGroupMember(String participantId, String maraudGroupId) {
        this.maraudGroupMemberRepository.delete(this.maraudGroupMemberRepository.findByMaraudGroupIdAndMemberId(maraudGroupId, participantId));
    }
}
