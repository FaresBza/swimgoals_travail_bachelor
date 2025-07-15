package com.swimgoals.controller;

import java.util.List;
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

import com.swimgoals.dto.ObjectiveDTO;
import com.swimgoals.models.Group;
import com.swimgoals.models.Objective;
import com.swimgoals.service.impl.ObjectiveServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class ObjectiveController {
    
    private final ObjectiveServiceImpl objectiveService;

    public ObjectiveController(ObjectiveServiceImpl objectiveService) {
        this.objectiveService = objectiveService;
    }

    @Operation(summary = "Retrieve all objectives of one swimmer", description = "Retrieves a list of all objectives of one swimmer available in the database", tags = {
        "Objective" })
    @ApiResponse(responseCode = "200", description = "List of objectives of one swimmer retrieved successfully", content = @Content(schema = @Schema(implementation = Objective.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @GetMapping("/objectives/swimmer/{swimmerId}")
    public ResponseEntity<List<Objective>> getObjectivesBySwimmerId(@PathVariable int swimmerId) {
        List<Objective> objectives = objectiveService.getObjectiveBySwimmerId(swimmerId);
        return ResponseEntity.ok(objectives);
    }

    @Operation(summary = "Create a new objective", description = "Creates a new objective in the database and returns the created objective object", tags = {
            "Objective" })
    @ApiResponse(responseCode = "200", description = "Objective created successfully", content = @Content(schema = @Schema(implementation = Objective.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @PostMapping("/objectives")
    public ResponseEntity<?> createObjective(@RequestBody ObjectiveDTO objectiveDTO) {
        try {
            Objective createdObjective = objectiveService.creaObjective(objectiveDTO);
            return ResponseEntity.ok(createdObjective);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue");
        }
    }

    @Operation( summary = "Retrieve a objective by ID", description = "Retrieves a objective by their unique ID from the database", tags = {
        "Objective" })
    @ApiResponse(responseCode = "200", description = "Objective object retrieved successfully", content = @Content(schema = @Schema(implementation = Objective.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @GetMapping("/objective/{id}")
    public ResponseEntity<Optional<Objective>> getGroupById(@PathVariable Integer id) {
        try {
            Optional<Objective> objective = objectiveService.getObjectiveById(id);
            return ResponseEntity.ok(objective);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
