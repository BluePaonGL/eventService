package fr.isep.eventService.application.controller;

import fr.isep.eventService.application.DTO.EventDto;
import fr.isep.eventService.application.DTO.EventParticipantDto;
import fr.isep.eventService.application.port.EventServicePort;
import fr.isep.eventService.domain.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
@Validated
@Slf4j
public class EventController {

    private final EventServicePort eventServicePort;

    @PostMapping("/addEvent")
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto) {
        return ResponseEntity.ok(this.eventServicePort.saveEvent(eventDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvent() {
        return new ResponseEntity<>(this.eventServicePort.getEvents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id){
        return new ResponseEntity<>(this.eventServicePort.getEvent(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable String id){
        this.eventServicePort.deleteEvent(id);
    }


    @PostMapping("/participant")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEventParticipant(@RequestBody @Valid EventParticipantDto eventParticipantDTO) throws DuplicateKeyException {
        try {
            this.eventServicePort.saveEventParticipant(eventParticipantDTO);
        } catch (DuplicateKeyException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "User is already registred to this event", e
            );
        }

    }

    //TODO mettre dans une requête de filtrage qui renvoit une Page contenant les events en fonctions des différents critères.
    @GetMapping("/participant/{participantId}")
    public ResponseEntity<List<Event>> getEventsByParticipantId(@PathVariable String participantId){
        return ResponseEntity.ok(this.eventServicePort.getEventsByParticipantId(participantId));
    }

    @DeleteMapping("/{eventId}/participant/{participantId}")
    public void deleteEventParticipant(@PathVariable String eventId, @PathVariable String participantId){
        this.eventServicePort.deleteParticipant(eventId, participantId);
    }

}
