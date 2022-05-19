package fr.isep.eventService.application.DTO;

import fr.isep.eventService.domain.model.Event;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventParticipantDto {
    @NotNull
    @NotEmpty
    private String participantId;

    @NotNull
    @NotEmpty
    private String eventId;
}
