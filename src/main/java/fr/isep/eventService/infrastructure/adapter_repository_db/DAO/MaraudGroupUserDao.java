package fr.isep.eventService.infrastructure.adapter_repository_db.DAO;

import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupDao;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaraudGroupUserDao {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String userId;

    @ManyToMany(mappedBy = "users")
    Set<MaraudGroupDao> listOfMaraudGroups;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaraudGroupUserDao that = (MaraudGroupUserDao) o;
        return userId != null && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
