package fr.isep.eventService.infrastructure.adatpter_repository_db.adapter;


import fr.isep.eventService.domain.model.MaraudGroupUser;
import fr.isep.eventService.domain.port.MaraudGroupUserRepositoryPort;
import fr.isep.eventService.infrastructure.adatpter_repository_db.DAO.MaraudGroupUserDao;
import fr.isep.eventService.infrastructure.adatpter_repository_db.repository.MaraudGroupUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class MaraudGroupUserRepositoryAdapter implements MaraudGroupUserRepositoryPort {

    private MaraudGroupUserRepository maraudGroupUserRepository;
    private final ModelMapper modelMapper;

    @Override
    public MaraudGroupUser findById(String userId){
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
}
