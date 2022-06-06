package fr.isep.eventService.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class MaraudGroup {
    private String maraudGroupId;
    private String eventId;
    private String groupLabel;
    private List<String> maraudGroupMembers;
}
