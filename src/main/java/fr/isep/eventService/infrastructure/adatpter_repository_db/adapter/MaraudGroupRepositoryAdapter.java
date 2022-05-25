package fr.isep.eventService.infrastructure.adatpter_repository_db.adapter;

import fr.isep.eventService.domain.criteria.MaraudGroupCriteria;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.domain.port.MaraudGroupRepositoryPort;
import fr.isep.eventService.infrastructure.adatpter_repository_db.DAO.MaraudGroupDao;
import fr.isep.eventService.infrastructure.adatpter_repository_db.repository.MaraudGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MaraudGroupRepositoryAdapter implements MaraudGroupRepositoryPort {

    private MaraudGroupRepository maraudGroupRepository;
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
    public MaraudGroup findByEventId(String eventId){
        MaraudGroupDao maraudGroupDao = this.maraudGroupRepository.findByEventId(eventId);
        try{
            return this.modelMapper.map(maraudGroupDao, MaraudGroup.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This event does not exist in the database", exception);
        }
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
        List<MaraudGroupDao> maraudGroupDaoList =this.maraudGroupRepository.findMaraudGroupDaoByListOfUsersContains(userId);
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

    //TODO save here ?
    //TODO TEST
    @Override
    public MaraudGroup addUserToMaraudGroup(String groupId, String userId){
        MaraudGroupDao maraudGroupDao = this.maraudGroupRepository.findByGroupId(groupId);
        List<String> listOfUsers = maraudGroupDao.getListOfUsers();
        listOfUsers.add(userId);
        maraudGroupDao.setListOfUsers(listOfUsers);
        return this.modelMapper.map(this.maraudGroupRepository.save(maraudGroupDao), MaraudGroup.class);
    }

    @Override
    public void delete(String groupId){
        MaraudGroupDao maraudGroupDao = this.maraudGroupRepository.getById(Long.valueOf(groupId));
        this.maraudGroupRepository.delete(maraudGroupDao);
    }
}
