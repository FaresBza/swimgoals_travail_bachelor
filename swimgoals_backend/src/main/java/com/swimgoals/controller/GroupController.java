package com.swimgoals.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.swimgoals.models.Group;
import com.swimgoals.service.GroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class GroupController {

    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Erreur interne du serveur";
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @Operation(summary = "Retrieve all groups", description = "Retrieves a list of all groups available in the database", tags = {
            "Group" })
    @ApiResponse(responseCode = "200", description = "List of groups retrieved successfully", content = @Content(schema = @Schema(implementation = Group.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @Operation(summary = "Retrieve a group by ID", description = "Retrieves a group object from the database using its ID", tags = {
            "Group" })
    @ApiResponse(responseCode = "200", description = "Group retrieved successfully", content = @Content(schema = @Schema(implementation = Group.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @GetMapping("/groups/{coachId}")
    public ResponseEntity<List<Group>> getAllGroupsByCoachId(@PathVariable("coachId") String coachId) {
        try {
            List<Group> groups = groupService.getAllGroupsByCoachId(coachId);
            return ResponseEntity.ok(groups);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ID du coach ne peut pas être nul");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @Operation(summary = "Create a new group", description = "Creates a new group in the database and returns the created group object", tags = {
            "Group" })
    @ApiResponse(responseCode = "200", description = "Group created successfully", content = @Content(schema = @Schema(implementation = Group.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @PostMapping("/groups")
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

    @Operation(summary = "Update a group", description = "Update group informations in the database and returns the updated group object", tags = {
            "Group" })
    @ApiResponse(responseCode = "200", description = "Group updated successfully", content = @Content(schema = @Schema(implementation = Group.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @PutMapping("/groups/{groupId}")
    public ResponseEntity<Group> updateGroup(@PathVariable("groupId") int groupId, @RequestBody Group group) {
        try {
            Group updatedGroup = groupService.updateGroup(groupId, group);
            return ResponseEntity.ok(updatedGroup);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ID du groupe ne peut pas être nul");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE, e);
        }
    }

    @Operation(summary = "Delete a group", description = "Delete a group from the database using its ID", tags = {
            "Group" })
    @ApiResponse(responseCode = "200", description = "Group deleted successfully", content = @Content(schema = @Schema(implementation = Group.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @DeleteMapping("/groups/{groupId}")
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
