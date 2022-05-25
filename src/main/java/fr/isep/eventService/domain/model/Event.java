package fr.isep.eventService.domain.model;

import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventType;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
public class Event {
    private String eventId;
    private String name;
    private EventType eventType;
    private String startingCampus;
    private String location;
    private Date date;
    private Date startingTime;
    private Date endingTime;
    private String description;
    private List<String> participantsId;
}
