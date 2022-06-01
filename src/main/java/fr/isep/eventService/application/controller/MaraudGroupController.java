package fr.isep.eventService.application.controller;

import fr.isep.eventService.application.DTO.MaraudGroupDto;
import fr.isep.eventService.application.port.MaraudGroupServicePort;
import fr.isep.eventService.domain.model.MaraudGroup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maraud")
@Slf4j
@Validated
public class MaraudGroupController {
    private final MaraudGroupServicePort maraudGroupServicePort;

    @PostMapping()
    public ResponseEntity<MaraudGroup> createMaraudGroup(@Valid @RequestBody MaraudGroupDto maraudGroupDto){
        return ResponseEntity.ok(this.maraudGroupServicePort.saveMaraudGroup(maraudGroupDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaraudGroup>> getAllMaraudGroup(){
        return new ResponseEntity<>(this.maraudGroupServicePort.getMaraudsGroups(), HttpStatus.OK);
    }

    @GetMapping("/bygroupid/{groupId}")
    public ResponseEntity<MaraudGroup> getMaraudGroupByGroupId(@PathVariable String groupId){
        return new ResponseEntity<>(this.maraudGroupServicePort.getMaraudGroupById(groupId), HttpStatus.OK);
    }

    @GetMapping("/byeventid/{eventId}")
    public ResponseEntity<MaraudGroup> getMaraudGroupByEventId(@PathVariable String eventId){
        return new ResponseEntity<>(this.maraudGroupServicePort.getMaraudGroupByEventId(eventId), HttpStatus.OK);
    }

    @GetMapping("/bygrouplabel/{groupLabel}")
    public ResponseEntity<MaraudGroup> getMaraudGroupByGroupLabel(@PathVariable String groupLabel){
        return new ResponseEntity<>(this.maraudGroupServicePort.getMaraudGroupByGroupLabel(groupLabel), HttpStatus.OK);
    }

    @GetMapping("/groupofuser/{userId}")
    public ResponseEntity<List<MaraudGroup>> getListOfMaraudGroupByUserIdIn(@PathVariable String userId){
        return new ResponseEntity<>(this.maraudGroupServicePort.getListOfMaraudGroupByUserIdIn(userId), HttpStatus.OK);
    }
}
