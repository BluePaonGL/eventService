package fr.isep.eventService.infrastructure.adapter_repository_db.DAO;

import fr.isep.eventService.domain.model.Event;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventParticipantDAO {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String eventParticipantId;

    private String participantId;

    private String eventId;
}
