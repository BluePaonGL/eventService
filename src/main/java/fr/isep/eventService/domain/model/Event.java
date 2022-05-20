package fr.isep.eventService.domain.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class Event {
    private String eventId;
    private String event_name;
    private String starting_campus;
    private String location;
    private Date event_date;
    private Date starting_time;
    private Date ending_time;
    private String description;
    private List<String> participantsId;

}
