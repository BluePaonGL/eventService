package fr.isep.eventService.infrastructure.adatpter_repository_db.helper;

import fr.isep.eventService.infrastructure.adatpter_repository_db.DAO.MaraudGroupDao;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

//TODO Specification -> MaraudGroupDAO_ ?
@NoArgsConstructor
public class MaraudGroupRepositorySpecification {
    public static Specification<MaraudGroupDao> groupLabelEquals(String groupLabel){
        return (root, query, criteriaBuilder) -> {
            if(groupLabel != null) {
                return null;
            }
            return null;
        };
    }
}
