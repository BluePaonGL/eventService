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
    private String event_name;

    private String starting_campus;

    private String location;

    @NotNull
    @NotEmpty
    private Date event_date;

    private Time starting_time;

    private Time ending_time;

    private String description;

    private List<String> participants;

}
