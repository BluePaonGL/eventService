package fr.isep.eventService.application.DTO;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Setter(value = AccessLevel.NONE)
public class MaraudGroupDto {
    @NotNull
    @NotEmpty
    private String groupLabel;
    private String eventId;
    //TODO eventId ?
}
