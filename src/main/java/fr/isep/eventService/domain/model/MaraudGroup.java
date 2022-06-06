package fr.isep.eventService.domain.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class MaraudGroup {
    private String groupId;
    private String eventId;
    private String groupLabel;
    private List<String> listOfUsers;
}
