package fr.isep.eventService.infrastructure.adapter_repository_db.DAO;

import fr.isep.eventService.domain.model.Event;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EventDAO {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String eventId;

    private String event_name;
    private String starting_campus;
    private String location;

    @Temporal(TemporalType.DATE)
    private Date event_date;

    @Temporal(TemporalType.TIME)
    private Date starting_time;

    @Temporal(TemporalType.TIME)
    private Date ending_time;

    private String description;

    @OneToMany(targetEntity = EventParticipantDAO.class)
    private List<EventParticipantDAO> eventParticipantDAO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EventDAO event = (EventDAO) o;
        return eventId != null && Objects.equals(eventId, event.eventId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}