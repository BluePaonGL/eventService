package fr.isep.eventService.application.controller;


import fr.isep.eventService.application.DTO.MaraudGroupDto;
import fr.isep.eventService.application.DTO.MaraudGroupUserDto;
import fr.isep.eventService.application.port.MaraudGroupUserServicePort;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.domain.model.MaraudGroupUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maraud/user")
@Slf4j
@Validated
public class MaraudGroupUserController {

    private final MaraudGroupUserServicePort maraudGroupUserServicePort;

    @PostMapping("/create")
    public ResponseEntity<MaraudGroupUser> createMaraudGroupUser(@Valid @RequestBody MaraudGroupUserDto maraudGroupUserDto){
        return ResponseEntity.ok(this.maraudGroupUserServicePort.saveMaraudGroupUser(maraudGroupUserDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaraudGroupUser>> getAllMaraudGroupUsers(){
        return new ResponseEntity<>(this.maraudGroupUserServicePort.getMaraudGroupUsers(), HttpStatus.OK);
    }

    @GetMapping("/nogroup/{eventId}")
    public ResponseEntity<List<MaraudGroupUser>> getAllUsersWithNoGroupForEvent(@PathVariable String eventId){
        return new ResponseEntity<>(this.maraudGroupUserServicePort.getAllUsersWithNoGroupForEvent(eventId), HttpStatus.OK);
    }
}
