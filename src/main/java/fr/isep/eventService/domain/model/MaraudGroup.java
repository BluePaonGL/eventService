package fr.isep.eventService.domain.model;

import lombok.Data;

@Data
public class MaraudGroup {
    private Long groupId;
    private Long eventId;
    private String groupLabel;
}
