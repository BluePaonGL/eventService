package fr.isep.eventService.infrastructure.adapter_repository_db.DAO;

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
public class MaraudGroupDao {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String groupId;
    private String eventId;
    private String groupLabel;

    @OneToMany(targetEntity = MaraudGroupUserDao.class)
    Set<MaraudGroupUserDao> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaraudGroupDao that = (MaraudGroupDao) o;
        return groupId != null && Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
