package fr.isep.eventService.application.controller;

import fr.isep.eventService.application.DTO.EventDto;
import fr.isep.eventService.application.port.EventServicePort;
import fr.isep.eventService.domain.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
public class EventController {

    private final EventServicePort eventServicePort;

    @PostMapping()
    public ResponseEntity<Event> createEvent(/*@Valid*/ @RequestBody EventDto eventDto) {
        return ResponseEntity.ok(this.eventServicePort.saveEvent(eventDto));
    }

    @GetMapping()
    public ResponseEntity<List<Event>> getAllUser() {
        return new ResponseEntity<>(this.eventServicePort.getEvents(), HttpStatus.OK);
    }

}
