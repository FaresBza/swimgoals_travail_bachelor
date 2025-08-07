package com.swimgoals.repository;

import com.swimgoals.models.Goal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
public class GoalRepositoryTest {

    @Autowired
    private GoalRepository goalRepository;

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
}
