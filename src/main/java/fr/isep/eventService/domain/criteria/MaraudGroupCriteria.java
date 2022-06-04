package fr.isep.eventService.domain.criteria;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MaraudGroupCriteria {
    private String groupLabel;
    private Integer pageNumber;
    private Integer pageSize;
}
