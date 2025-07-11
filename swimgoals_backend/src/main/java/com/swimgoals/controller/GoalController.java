package com.swimgoals.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swimgoals.dto.GoalDTO;
import com.swimgoals.models.Goal;
import com.swimgoals.service.GoalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api")
public class GoalController {
    
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @Operation(summary = "Retrieve all goals of one objective", description = "Retrieves a list of all goals of one objective available in the database", tags = {
            "Goal" })
    @ApiResponse(responseCode = "200", description = "List of goals of one objective retrieved successfully", content = @Content(schema = @Schema(implementation = Goal.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @GetMapping("/goals/{objectiveId}")
    public ResponseEntity<List<Goal>> getGoalsByObjective(@PathVariable Integer objectiveId) {
        List<Goal> goals = goalService.getGoalsByObjectiveId(objectiveId);
        return ResponseEntity.ok(goals);
    }

    @Operation(summary = "Create a new goal of an objetive", description = "Creates a new goal of an objetive in the database and returns the created objective object", tags = {
            "Goal" })
    @ApiResponse(responseCode = "200", description = "Goal created successfully", content = @Content(schema = @Schema(implementation = Goal.class), mediaType = "application/json"))
    @ApiResponse(responseCode = "400", description = "Invalid credentials", content = @Content(schema = @Schema()))
    @PostMapping("/goals")
    public ResponseEntity<?> createGoal(@RequestBody GoalDTO goalDTO) {
        try {
            Goal createdGoal = goalService.createGoal(goalDTO);
            return ResponseEntity.ok(createdGoal);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue");
        }
    }

}
