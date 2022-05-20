package fr.isep.eventService.application.controller;

import fr.isep.eventService.application.DTO.EventDto;
import fr.isep.eventService.application.DTO.EventParticipantDto;
import fr.isep.eventService.application.port.EventServicePort;
import fr.isep.eventService.domain.model.Event;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.EventParticipantDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
@Validated
public class EventController {

    private final EventServicePort eventServicePort;

    @PostMapping("/addEvent")
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto) {
        return ResponseEntity.ok(this.eventServicePort.saveEvent(eventDto));
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvent() {
        return new ResponseEntity<>(this.eventServicePort.getEvents(), HttpStatus.OK);
    }

    @GetMapping("/findEvent/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id){
        return new ResponseEntity<>(this.eventServicePort.getEvent(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public void deleteEvent(@PathVariable String id){
        this.eventServicePort.deleteEvent(id);
    }


    @PostMapping("/addEventParticipant")
    public ResponseEntity<EventParticipantDAO> createEventParticipant(@RequestBody @Valid EventParticipantDto eventParticipantDTO) {
        return ResponseEntity.ok(this.eventServicePort.saveEventParticipant(eventParticipantDTO));
    }

    @GetMapping("/eventsByParticipantId/{participantId}")
    public ResponseEntity<List<Event>> getEventsByParticipantId(@PathVariable String participantId){
        return ResponseEntity.ok(this.eventServicePort.getEventsByParticipantId(participantId));
    }

    @DeleteMapping("/deleteParticipant/{eventId}/{participantId}")
    public void deleteEventParticipant(@PathVariable String eventId, @PathVariable String participantId){
        this.eventServicePort.deleteParticipant(eventId, participantId);
    }

}
