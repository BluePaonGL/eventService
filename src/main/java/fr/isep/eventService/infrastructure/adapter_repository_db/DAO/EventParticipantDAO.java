package fr.isep.eventService.infrastructure.adapter_repository_db.DAO;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="`event_participant`")
public class EventParticipantDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String eventParticipantId;

    private String participantId;

    @ManyToOne
    @JoinColumn(name = "eventId")
    private EventDAO event;
}
