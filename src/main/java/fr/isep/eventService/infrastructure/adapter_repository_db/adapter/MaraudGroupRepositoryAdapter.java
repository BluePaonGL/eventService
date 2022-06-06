package fr.isep.eventService.infrastructure.adapter_repository_db.adapter;

import fr.isep.eventService.domain.criteria.MaraudGroupCriteria;
import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.domain.model.MaraudGroupUser;
import fr.isep.eventService.domain.port.MaraudGroupRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupUserDao;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.MaraudGroupRepository;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.MaraudGroupUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MaraudGroupRepositoryAdapter implements MaraudGroupRepositoryPort {

    private MaraudGroupRepository maraudGroupRepository;
    private MaraudGroupUserRepository maraudGroupUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public MaraudGroup findById(String groupId){
        MaraudGroupDao maraudGroupDao = this.maraudGroupRepository.findByGroupId(groupId);
        try{
            return this.modelMapper.map(maraudGroupDao, MaraudGroup.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This group does not exist in the database", exception);
        }
    }

    //TODO return list ?
    @Override
    public List<MaraudGroup> findByEventId(String eventId){
        List<MaraudGroupDao> maraudGroupDaoList = this.maraudGroupRepository.findAllByEventId(eventId);
        return maraudGroupDaoList.stream()
                .map(maraudGroupDao -> this.modelMapper.map(maraudGroupDao, MaraudGroup.class))
                .collect(Collectors.toList());
    }

    @Override
    public MaraudGroup findByGroupLabel(String groupLabel){
        MaraudGroupDao maraudGroupDao = this.maraudGroupRepository.findByGroupLabel(groupLabel);
        try{
            return this.modelMapper.map(maraudGroupDao, MaraudGroup.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This group does not exist in the database", exception);
        }
    }

    @Override
    public List<MaraudGroup> getListOfMaraudGroupByUserIdIn(String userId){
        List<MaraudGroupDao> maraudGroupDaoList =this.maraudGroupRepository.findAllByUsers_UserId(userId);
        return maraudGroupDaoList.stream()
                .map(maraudGroupDao -> this.modelMapper.map(maraudGroupDao, MaraudGroup.class))
                .collect(Collectors.toList());
    }

    @Override
    public MaraudGroup saveMaraudGroup(MaraudGroup maraudGroup){
        MaraudGroupDao maraudGroupDao = this.modelMapper.map(maraudGroup, MaraudGroupDao.class);
        return this.modelMapper.map(this.maraudGroupRepository.save(maraudGroupDao), MaraudGroup.class);
    }

    @Override
    public List<MaraudGroup> findAll(){
        return this.maraudGroupRepository.findAll()
                .stream().map(maraudGroupDao -> this.modelMapper.map(maraudGroupDao, MaraudGroup.class))
                .collect(Collectors.toList());
    }

    //TODO Specification -> Page
    @Override
    public Page<MaraudGroup> pageMaraudGroup(MaraudGroupCriteria maraudGroupCriteria){
        return null;
    }

    @Override
    public void delete(String groupId){
        MaraudGroupDao maraudGroupDao = this.maraudGroupRepository.getById(Long.valueOf(groupId));
        this.maraudGroupRepository.delete(maraudGroupDao);
    }

    @Override
    public MaraudGroup addUserToMaraudGroup(MaraudGroup maraudGroup, String userId){
        MaraudGroupDao maraudGroupDao = this.modelMapper.map(maraudGroup, MaraudGroupDao.class);
        MaraudGroupUserDao maraudGroupUserDao = this.maraudGroupUserRepository.save(MaraudGroupUserDao.builder()
                        .userId(userId)
                        .groupId(maraudGroup.getGroupId())
                        .build());

        Set<MaraudGroupUserDao> setMaraudGroupUser = maraudGroupDao.getUsers();
        setMaraudGroupUser.add(maraudGroupUserDao);
        maraudGroupDao.setUsers(setMaraudGroupUser);

        return this.modelMapper.map(this.maraudGroupRepository.save(maraudGroupDao), MaraudGroup.class);
    }

    @Override
    public MaraudGroup removeUserFromMaraudGroup(MaraudGroup maraudGroup, String userId){
        MaraudGroupDao maraudGroupDao = this.modelMapper.map(maraudGroup, MaraudGroupDao.class);
        MaraudGroupUserDao maraudGroupUserDao = this.maraudGroupUserRepository.findByUserId(userId);

        Set<MaraudGroupUserDao> setMaraudGroupUser = maraudGroupDao.getUsers();
        setMaraudGroupUser.remove(maraudGroupUserDao);
        maraudGroupDao.setUsers(setMaraudGroupUser);

        this.modelMapper.map(this.maraudGroupUserRepository.save(maraudGroupUserDao), MaraudGroupUser.class);
        return this.modelMapper.map(this.maraudGroupRepository.save(maraudGroupDao), MaraudGroup.class);
    }

    @Override
    public List<String> getAllUserInGroup(String groupId) {
        return this.maraudGroupUserRepository.findByGroupId(groupId)
                .stream().map(MaraudGroupUserDao::getUserId)
                .collect(Collectors.toList());
    }
}
