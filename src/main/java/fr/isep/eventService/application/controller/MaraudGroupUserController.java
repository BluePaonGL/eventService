package fr.isep.eventService.application.controller;


import fr.isep.eventService.application.port.MaraudGroupUserServicePort;
import fr.isep.eventService.domain.model.MaraudGroupUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/maraud/user")
@Slf4j
@Validated
public class MaraudGroupUserController {

    private final MaraudGroupUserServicePort maraudGroupUserServicePort;

    @GetMapping("/all")
    public ResponseEntity<List<MaraudGroupUser>> getAllMaraudGroupUsers(){
        return new ResponseEntity<>(this.maraudGroupUserServicePort.getMaraudGroupUsers(), HttpStatus.OK);
    }
}
