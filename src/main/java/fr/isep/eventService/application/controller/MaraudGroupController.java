package fr.isep.eventService.application.controller;

import fr.isep.eventService.application.DTO.MaraudGroupDto;
import fr.isep.eventService.application.DTO.MaraudGroupMemberDto;
import fr.isep.eventService.application.port.MaraudGroupServicePort;
import fr.isep.eventService.domain.model.MaraudGroup;
import fr.isep.eventService.infrastructure.adapter_repository_db.DAO.MaraudGroupMemberDAO;
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
@RequestMapping("/maraud")
@Slf4j
@Validated
public class MaraudGroupController {
    private final MaraudGroupServicePort maraudGroupServicePort;

    @PostMapping("/create")
    public ResponseEntity<MaraudGroup> createMaraudGroup(@RequestBody @Valid MaraudGroupDto maraudGroupDto) {
        return ResponseEntity.ok(this.maraudGroupServicePort.saveMaraudGroup(maraudGroupDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MaraudGroup>> getAllMaraudGroup() {
        return new ResponseEntity<>(this.maraudGroupServicePort.getMaraudGroups(), HttpStatus.OK);
    }

    @GetMapping("/bygroupid/{groupId}")
    public ResponseEntity<MaraudGroup> getMaraudGroupByGroupId(@PathVariable String groupId) {
        return new ResponseEntity<>(this.maraudGroupServicePort.getMaraudGroupById(groupId), HttpStatus.OK);
    }

    @GetMapping("/byeventid/{eventId}")
    public ResponseEntity<List<MaraudGroup>> getMaraudGroupByEventId(@PathVariable String eventId) {
        return new ResponseEntity<>(this.maraudGroupServicePort.getListOfMaraudGroupByEventId(eventId), HttpStatus.OK);
    }

    @GetMapping("/groupofmember/{memberId}")
    public ResponseEntity<List<MaraudGroup>> getAllMaraudGroupsByMemberId(@PathVariable String memberId) {
        return new ResponseEntity<>(this.maraudGroupServicePort.getAllMaraudGroupsByMemberId(memberId), HttpStatus.OK);
    }

    @PostMapping("/addmaraudgroupmember")
    public ResponseEntity<MaraudGroupMemberDAO> addUserToMaraudGroup(@RequestBody @Valid MaraudGroupMemberDto maraudGroupMemberDto) {
        return ResponseEntity.ok(this.maraudGroupServicePort.saveMaraudGroupMember(maraudGroupMemberDto));
    }

    @DeleteMapping("/removeuser/{groupId}/{userId}")
    public void removeUserFromMaraudGroup(@PathVariable String groupId, @PathVariable String userId) {
        this.maraudGroupServicePort.deleteMaraudGroupMember(userId, groupId);
    }

    @DeleteMapping("/deletemaraudgroup/{maraudGroupId}")
    public void deleteMaraudGroup(@PathVariable String maraudGroupId) {
        this.maraudGroupServicePort.deleteMaraudGroup(maraudGroupId);
    }
}
