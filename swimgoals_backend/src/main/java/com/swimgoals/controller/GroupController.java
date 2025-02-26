package com.swimgoals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.swimgoals.service.GroupService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.swimgoals.models.Group;
import com.swimgoals.models.User;


@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/groups/{coachId}")
    public ResponseEntity<List<Group>> getAllGroupsByCoachId(@PathVariable("coachId") User coach) {
        List<Group> groups = groupService.getAllGroupsByCoachId(coach);
        return ResponseEntity.ok(groups);
    }
    
    @PostMapping("/createGroup")
    public ResponseEntity<Group> createGroup(@RequestBody Group group){
        Group createdGroup = groupService.createGroup(group);
        return ResponseEntity.ok(createdGroup);
    }

    @PostMapping("/updateGroup")
    public ResponseEntity<Group> updateGroup(Integer groupId, Group group){
        Group updatedGroup = groupService.updateGroup(groupId, group);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<Group> deleteGroup(@PathVariable("groupId") int groupId){
        groupService.deleteGroup(groupId);
        return ResponseEntity.noContent().build();
    }
    
}
