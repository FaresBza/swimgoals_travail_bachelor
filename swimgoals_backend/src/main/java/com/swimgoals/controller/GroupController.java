package com.swimgoals.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.swimgoals.service.GroupService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.swimgoals.models.Group;


@RestController
@RequestMapping("/groups")
public class GroupController {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Erreur interne du serveur";
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{coachId}")
public ResponseEntity<List<Group>> getAllGroupsByCoachId(@PathVariable("coachId") UUID coachId) {
    try {
        List<Group> groups = groupService.getAllGroupsByCoachId(coachId);
        return ResponseEntity.ok(groups);
    } catch (IllegalArgumentException e) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ID du coach ne peut pas être nul");
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
    }
}


    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        try {
            Group createdGroup = groupService.createGroup(group);
            return ResponseEntity.ok(createdGroup);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Les données du groupe sont invalides");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<Group> updateGroup(@RequestParam Integer groupId, @RequestBody Group group) {
        try {
            Group updatedGroup = groupService.updateGroup(groupId, group);
            return ResponseEntity.ok(updatedGroup);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ID du groupe ne peut pas être nul");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("groupId") int groupId) {
        try {
            groupService.deleteGroup(groupId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ID du groupe est invalide");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }
    
}
