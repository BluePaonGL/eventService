package fr.isep.eventService.domain.model;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class Event {
    private String eventId;
    private String name;
    private String startingCampus;
    private String location;
    private Date date;
    private Date startingTime;
    private Date endingTime;
    private String description;
    private List<String> participantsId;

}
