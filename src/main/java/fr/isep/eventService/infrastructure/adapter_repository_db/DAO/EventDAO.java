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

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name="event_type")
    private EventType eventType;

    private String startingCampus;
    private String location;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date startingTime;

    @Temporal(TemporalType.TIME)
    private Date endingTime;

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