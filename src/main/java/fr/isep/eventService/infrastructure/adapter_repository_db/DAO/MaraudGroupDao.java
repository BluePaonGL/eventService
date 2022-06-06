package fr.isep.eventService.infrastructure.adapter_repository_db.DAO;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


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
    private String maraudGroupId;

    private String eventId;

    private String groupLabel;

    @OneToMany(targetEntity = MaraudGroupMemberDAO.class)
    private List<MaraudGroupMemberDAO> maraudGroupMembers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MaraudGroupDao that = (MaraudGroupDao) o;
        return maraudGroupId != null && Objects.equals(maraudGroupId, that.maraudGroupId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
