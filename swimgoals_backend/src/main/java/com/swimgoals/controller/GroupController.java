package com.swimgoals.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/groups/{coachId}")
    public ResponseEntity<List<Group>> getAllGroupsByCoachId(@PathVariable("coachId") UUID coachId) {
        List<Group> groups = groupService.getAllGroupsByCoachId(coachId);
        return ResponseEntity.ok(groups);
    }

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        Group createdGroup = groupService.createGroup(group);
        return ResponseEntity.ok(createdGroup);
    }

    @PostMapping("/update")
    public ResponseEntity<Group> updateGroup(@RequestParam Integer groupId, @RequestBody Group group) {
        Group updatedGroup = groupService.updateGroup(groupId, group);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("groupId") int groupId) {
        groupService.deleteGroup(groupId);
        return ResponseEntity.noContent().build();
    }
    
}
