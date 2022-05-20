package fr.isep.eventService.application.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
public class EventDto implements Serializable {
    @NotNull
    @NotEmpty
    private String name;

    private String startingCampus;

    private String location;

    @NotNull
    @NotEmpty
    private Date date;

    private Time startingTime;

    private Time endingTime;

    private String description;

    private List<String> participants;

}
