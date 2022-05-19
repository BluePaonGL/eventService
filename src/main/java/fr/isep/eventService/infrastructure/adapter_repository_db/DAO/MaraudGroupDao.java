package fr.isep.eventService.infrastructure.adapter_repository_db.DAO;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MaraudGroupDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String groupId;
    private String eventId;
    private String groupLabel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaraudGroupDao maraudGroup = (MaraudGroupDao) o;
        return groupId != null && Objects.equals(groupId, maraudGroup.groupId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
