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
            return modelMapper.map(maraudGroupDao, MaraudGroup.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This group does not exist in the database", exception);
        }
    }

    @Override
    public MaraudGroup findByEventId(String eventId){
        MaraudGroupDao maraudGroupDao = this.maraudGroupRepository.findByEventId(eventId);
        try{
            return modelMapper.map(maraudGroupDao, MaraudGroup.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This event does not exist in the database", exception);
        }
    }

    @Override
    public MaraudGroup findByGroupLabel(String groupLabel){
        MaraudGroupDao maraudGroupDao = this.maraudGroupRepository.findByGroupLabel(groupLabel);
        try{
            return modelMapper.map(maraudGroupDao, MaraudGroup.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This group does not exist in the database", exception);
        }
    }

    @Override
    public List<MaraudGroup> getListOfMaraudGroupByUserIdIn(String userId){
        List<MaraudGroupDao> maraudGroupDaoList =this.maraudGroupRepository.findMaraudGroupDaoByListOfUsersIn(userId);
        return maraudGroupDaoList.stream()
                .map(maraudGroupDao -> this.modelMapper.map(maraudGroupDao, MaraudGroup.class))
                .collect(Collectors.toList());
    }

    @Override
    public MaraudGroupDao saveMaraudGroup(MaraudGroup maraudGroup){
        MaraudGroupDao maraudGroupDao = modelMapper.map(maraudGroup, MaraudGroupDao.class);
        return maraudGroupRepository.save(maraudGroupDao);
    }

    @Override
    public List<MaraudGroup> findAll(){
        return this.maraudGroupRepository.findAll()
                .stream().map(maraudGroupDao -> modelMapper.map(maraudGroupDao, MaraudGroup.class))
                .collect(Collectors.toList());
    }

    //TODO Specification -> Page
    @Override
    public Page<MaraudGroup> pageMaraudGroup(MaraudGroupCriteria maraudGroupCriteria){
        return null;
    }

    //TODO save here ?
    @Override
    public MaraudGroupDao addUserToMaraudGroup(MaraudGroupDao maraudGroupDao, String userId){
        List<String> listOfUsers = maraudGroupDao.getListOfUsers();
        listOfUsers.add(userId);
        maraudGroupDao.setListOfUsers(listOfUsers);
        return maraudGroupRepository.save(maraudGroupDao);
    }

    @Override
    public void delete(String groupId){
        MaraudGroupDao maraudGroupDao = maraudGroupRepository.getById(Long.valueOf(groupId));
        maraudGroupRepository.delete(maraudGroupDao);
    }
}
