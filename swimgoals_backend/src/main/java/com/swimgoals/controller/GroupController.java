package com.swimgoals.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimgoals.dto.GroupDTO;
import com.swimgoals.dto.JoinGroupDTO;
import com.swimgoals.models.Group;
import com.swimgoals.service.impl.GroupServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class GroupController {

    private final GroupServiceImpl groupService;

    public GroupController(GroupServiceImpl groupService) {
        this.groupService = groupService;
    }

    @Operation(
        summary = "Retrieve all groups", 
        description = "Retrieves a list of all groups available in the database", 
        tags = { "Group" }
    )
    @ApiResponse(
        responseCode = "200", 
        description = "List of groups retrieved successfully", 
        content = @Content(schema = @Schema(implementation = Group.class), 
        mediaType = "application/json")
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid credentials", 
        content = @Content(schema = @Schema())
    )
    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @Operation(
        summary = "Retrieve all groups created by a coach", 
        description = "Retrieves a list of all groups created by a coach available in the database", 
        tags = { "Group" })
    @ApiResponse(
        responseCode = "200", 
        description = "List of groups retrieved successfully", 
        content = @Content(schema = @Schema(implementation = Group.class), 
        mediaType = "application/json")
    )
    @ApiResponse(
        responseCode = "400", 
        description = "Invalid credentials", 
        content = @Content(schema = @Schema())
    )
    @GetMapping("/groups/coach/{coachId}")
    public ResponseEntity<List<Group>> getGroupsByCoachId(@PathVariable int coachId) {
        List<Group> groups = groupService.getGroupsByCoachId(coachId);
        return ResponseEntity.ok(groups);
    }

    @Operation(summary = "Create a new group", description = "Creates a new group in the database and returns the created group object", tags = {
            "Group" })
    @ApiResponse(
        responseCode = "200", 
        description = "Group created successfully", 
        content = @Content(schema = @Schema(implementation = Group.class), 
        mediaType = "application/json"))
    @ApiResponse(
        responseCode = "400", 
        description = "Invalid credentials", 
        content = @Content(schema = @Schema())
    )
    @PostMapping("/groups")
    public ResponseEntity<?> createGroup(@RequestBody GroupDTO groupDTO) {
        try {
            Group createdGroup = groupService.createGroup(groupDTO);
            return ResponseEntity.ok(createdGroup);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue");
        }
    }

    @Operation(
        summary = "Join a group",
        description = "Adds a swimmer to an existing group using swimmerId and groupId.",
        tags = { "Group" }
    )
    @ApiResponse(
        responseCode = "200",
        description = "Swimmer successfully added to the group",
        content = @Content(mediaType = "application/json")
    )
    @ApiResponse(
        responseCode = "400",
        description = "Invalid swimmerId or groupId",
        content = @Content(mediaType = "application/json")
    )
    @PostMapping("/join-group")
    public ResponseEntity<?> joinGroup(@RequestBody JoinGroupDTO joinGroupDTO) {
        try {
            groupService.joinGroup(joinGroupDTO.getSwimmerId(), joinGroupDTO.getGroupId());
            return ResponseEntity.ok(Map.of("message", "Swimmer ajouté au groupe avec succès"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(
        summary = "Retrieve a group by ID", 
        description = "Retrieves a group by their unique ID from the database", 
        tags = { "Group" })
    @ApiResponse(
        responseCode = "200", 
        description = "Group object retrieved successfully", 
        content = @Content(schema = @Schema(implementation = Group.class), 
        mediaType = "application/json")
    )
    @ApiResponse(
        responseCode = "400", 
        description = "Invalid credentials", 
        content = @Content(schema = @Schema())
    )
    @GetMapping("/group/{id}")
    public ResponseEntity<Optional<Group>> getGroupById(@PathVariable Integer id) {
        try {
            Optional<Group> group = groupService.getGroupById(id);
            return ResponseEntity.ok(group);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
