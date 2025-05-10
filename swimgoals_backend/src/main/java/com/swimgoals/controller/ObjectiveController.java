package com.swimgoals.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimgoals.models.Group;
import com.swimgoals.models.Objective;
import com.swimgoals.service.ObjectiveService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/objectives")
public class ObjectiveController {
    
    private final ObjectiveService objectiveService;

    public ObjectiveController(ObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @Operation(summary = "Retrieve all objectives of one swimmer", description = "Retrieves a list of all objectives of one swimmer available in the database", tags = {
        "Objective" })
    @ApiResponse(responseCode = "200", description = "List of objectives of one swimmer retrieved successfully", content = @Content(schema = @Schema(implementation = Group.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @GetMapping("/swimmer/{swimmerId}")
    public ResponseEntity<List<Objective>> getObjectivesBySwimmerId(@PathVariable int swimmerId) {
        List<Objective> objectives = objectiveService.getObjectiveBySwimmerId(swimmerId);
        return ResponseEntity.ok(objectives);
    }
}
