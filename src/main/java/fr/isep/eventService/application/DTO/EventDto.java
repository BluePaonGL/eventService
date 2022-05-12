package fr.isep.eventService.application.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

@Data
@Setter(value = AccessLevel.NONE)
public class EventDto implements Serializable {
    @NotNull
    @NotEmpty
    private final String name;

    private String starting_campus;
    private String location;

    @NotNull
    @NotEmpty
    private Date date;

    private Time starting_time;

}
