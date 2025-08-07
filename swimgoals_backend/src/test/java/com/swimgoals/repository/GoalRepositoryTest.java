package com.swimgoals.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.swimgoals.models.Goal;
import com.swimgoals.models.Objective;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class GoalRepositoryTest {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Test
    void finAllGoals() {
        List<Goal> goals = goalRepository.findAll();
        assertEquals(5, goals.size(), "Il devrait y avoir 5 goals");

        for (Goal goal : goals) {
            assertTrue(goal.getId() > 0);
            assertTrue(goal.getTime() != null && !goal.getTime().isEmpty(), "le temps de l'objectif ne doit pas être vide");
            assertNotNull(goal.getObjective(), "Le goal doit être assigné à un objectif");
            assertNotNull(goal.getObjective().getId(), "L'objectif assigné à un goal doit avoir un ID");
            assertNotNull(goal.getDate(), "La date ne doit pas être null");
        }
    }

    @Test
    void findGoalById() {
        Optional<Goal> goal = goalRepository.findById(3);
        assertTrue(goal.isPresent(), "Goal non trouvé");
        assertEquals(3, goal.get().getId());
        assertEquals("200m", goal.get().getObjective().getDistance(), "La distance assigné au goal doit être '200m'");
        assertEquals("Dos", goal.get().getObjective().getSwim().getName(), "La nage assignée à l'objectif doit être 'Dos'");
        assertEquals("Emily", goal.get().getObjective().getSwimmer().getFirstname(), "Le prénom du nageur assigné doit être 'Emily'");
        assertEquals("Johnson", goal.get().getObjective().getSwimmer().getLastname(), "Le nom du nageur assigné doit être 'Johnson'");
    }

    @Test
    void testCreateGoal() {
        Objective objective = objectiveRepository.findById(4).orElseThrow();

        Goal goal = new Goal(
                6,
                objective,
                "00:1:23",
                "2024-04-13"
        );

        goalRepository.save(goal);
        Optional<Goal> createdGoal = goalRepository.findById(goal.getId());

        assertTrue(createdGoal.isPresent(), "Le goal ne doit pas être null");
        assertTrue(createdGoal.get().getId() > 0);
        assertTrue(createdGoal.get().getObjective().getId() > 0);
        assertNotNull(createdGoal.get().getDate());
        assertNotNull(createdGoal.get().getTime());
        assertEquals("00:1:23", goal.getTime(), "Le temps du goal doit être '00:1:23'");
        assertEquals("2024-04-13", goal.getDate(), "La date du goal doit être '2024-04-13'");
        assertEquals("100m", goal.getObjective().getDistance(), "La distance de l'objectif doit être '100m'");
        assertEquals("Brasse", goal.getObjective().getSwim().getName(), "La nage assignée à l'objectif doit être 'Brasse'");
        assertEquals("Michael", goal.getObjective().getSwimmer().getFirstname(), "Le prénom du nageur assigné doit être 'Michael'");
        assertEquals("Brown", goal.getObjective().getSwimmer().getLastname(), "Le nom du nageur assigné doit être 'Brown'");
    }
}
