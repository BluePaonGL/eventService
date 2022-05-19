package fr.isep.eventService.infrastructure.adapter_repository_db.adapter;

import fr.isep.eventService.domain.criteria.MaraudGroupCriteria;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.domain.port.MaraudGroupRepositoryPort;
import fr.isep.eventService.infrastructure.adapter_repository_db.repository.MaraudGroupRepository;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
@Component
public class MaraudGroupRepositoryAdapter implements MaraudGroupRepositoryPort {

    private MaraudGroupRepository maraudGroupRepository;
    private final ModelMapper modelMapper;

    @Override
    public MaraudGroup findById(String groupId){
        Optional<MaraudGroupDao> maraudGroupDaoOptional = this.maraudGroupRepository.findById(Long.valueOf(groupId));
        try{
            return modelMapper.map(maraudGroupDaoOptional.get(), MaraudGroup.class);
        } catch (NoSuchElementException exception){
            throw new NoSuchElementException("This maraud group does not exist in the database", exception);
        }
    }

    @Override
    public MaraudGroup findByEventId(String eventId){
        return null;
    }

    @Override
    public MaraudGroup findByGroupLabel(String groupLabel){
        return null;
    }

    @Override
    public MaraudGroupDao saveMaraudGroup(MaraudGroupDao maraudGroupDao){
        return maraudGroupRepository.save(maraudGroupDao);
    }

    @Override
    public List<MaraudGroup> findAll(){
        return null;
    }

    @Override
    public Page<MaraudGroup> pageMaraudGroup(MaraudGroupCriteria maraudGroupCriteria){
        return null;
    }

    @Override
    public void delete(String groupId){
        MaraudGroupDao maraudGroupDao = maraudGroupRepository.getById(Long.valueOf(groupId));

    }
}
