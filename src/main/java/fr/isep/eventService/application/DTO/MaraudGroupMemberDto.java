package fr.isep.eventService.application.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaraudGroupMemberDto {
    @NotNull
    @NotEmpty
    private String participantId;

    @NotNull
    @NotEmpty
    private String maraudGroupId;
}
