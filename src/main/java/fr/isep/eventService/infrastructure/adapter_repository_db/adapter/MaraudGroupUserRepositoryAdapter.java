package fr.isep.eventService.infrastructure.adapter_repository_db.adapter;


import fr.isep.eventService.domain.model.MaraudGroupUser;
import fr.isep.eventService.domain.port.MaraudGroupUserRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupUserDao;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.MaraudGroupRepository;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.MaraudGroupUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MaraudGroupUserRepositoryAdapter implements MaraudGroupUserRepositoryPort {

    private MaraudGroupUserRepository maraudGroupUserRepository;
    private final MaraudGroupRepository maraudGroupRepository;
    private final ModelMapper modelMapper;

    @Override
    public MaraudGroupUser findByUserId(String userId){
        MaraudGroupUserDao maraudGroupUserDao = this.maraudGroupUserRepository.findByUserId(userId);
        try{
            return this.modelMapper.map(maraudGroupUserDao, MaraudGroupUser.class);
        } catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("This user does not exist in the database", exception);
        }
    }

    @Override
    public MaraudGroupUser saveMaraudGroupUser(MaraudGroupUser maraudGroupUser){
        MaraudGroupUserDao maraudGroupUserDao = this.modelMapper.map(maraudGroupUser, MaraudGroupUserDao.class);
        return this.modelMapper.map(this.maraudGroupUserRepository.save(maraudGroupUserDao), MaraudGroupUser.class);
    }

    @Override
    public List<MaraudGroupUser> findAll(){
        return this.maraudGroupUserRepository.findAll()
                .stream().map(maraudGroupUserDao -> this.modelMapper.map(maraudGroupUserDao, MaraudGroupUser.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String userId){
        MaraudGroupUserDao maraudGroupUserDao = this.maraudGroupUserRepository.getById(Long.valueOf(userId));
        this.maraudGroupUserRepository.delete(maraudGroupUserDao);
    }

    @Override
    public List<MaraudGroupUser> getAllUsersWithNoGroupForEvent(String eventId, List<String> participantsIdList){
        //TODO récupérer les groups de chaque user, puis l'ajouter à une liste s'il n'a pas de groupe pour cet event
        List<MaraudGroupUserDao> listOfUsersWithoutGroup = new ArrayList<>();
        for(String participantId : participantsIdList){
            List<MaraudGroupDao> maraudGroupDaoList = this.maraudGroupRepository.findAllByUsers_UserId(participantId);
            boolean hasGroup = false;
            for(MaraudGroupDao maraudGroupDao : maraudGroupDaoList){
                if(maraudGroupDao.getEventId() == eventId){
                    hasGroup = true;
                }
            }
            if(!hasGroup){
                listOfUsersWithoutGroup.add(this.maraudGroupUserRepository.findByUserId(participantId));
            }
        }
        return listOfUsersWithoutGroup.stream()
                .map(maraudGroupUserDao -> this.modelMapper.map(maraudGroupUserDao, MaraudGroupUser.class))
                .collect(Collectors.toList());
    }
}
