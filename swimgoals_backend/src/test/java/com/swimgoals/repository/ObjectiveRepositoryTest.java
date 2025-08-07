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

import com.swimgoals.models.Objective;
import com.swimgoals.models.Swim;
import com.swimgoals.models.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class ObjectiveRepositoryTest {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SwimRepository swimRepository;

    @Test
    void testFindAllObjectives() {
        List<Objective> objectives = objectiveRepository.findAll();
        assertEquals(4, objectives.size(), "Il devrait y avoir 4 objectifs");

        for (Objective objective : objectives) {
            assertTrue(objective.getId() > 0);
            assertTrue(objective.getDistance() != null && !objective.getDistance().isEmpty(), "la distance de l'objectif ne doit pas être vide");
            assertTrue(objective.getTime() != null && !objective.getTime().isEmpty(), "le time de l'objectif ne doit pas être vide");
            assertNotNull(objective.getSwimmer(), "L'objectif doit être assigné à un nageur");
            assertNotNull(objective.getSwimmer().getId(), "Le nageur assigné à l'objectif doit avoir un ID");
            assertNotNull(objective.getSwim(), "L'objectif doit être assigné à une nage");
            assertTrue(objective.getSwim().getId() > 0, "La nage assigné à l'objectif doit avoir un ID");
        }
    }

    @Test
    void testFindObjectiveById() {
        Optional<Objective> objective = objectiveRepository.findById(3);
        assertTrue(objective.isPresent(), "Objectif non trouvé");
        assertEquals(6, objective.get().getSwimmer().getId(), "");
        assertEquals("Papillon", objective.get().getSwim().getName(), "La nage assigné à l'objectif doit être 'Papillon'");
        assertNotNull(objective.get().getDistance());
        assertNotNull(objective.get().getTime());
    }

    @Test
    void testFindObjectiveBySwimmerId() {
        List<Objective> ListSwimmerObjectives = objectiveRepository.findBySwimmerId(5);
        assertEquals(1, ListSwimmerObjectives.size());

        for (Objective objective : ListSwimmerObjectives) {
            assertNotNull(objective, "L'objectif ne doit pas être null");
            assertTrue(objective.getId() > 0);
            assertTrue(objective.getSwimmer().getId() > 0);
            assertNotNull(objective.getDistance());
            assertNotNull(objective.getTime());
            assertEquals("200m", objective.getDistance(), "La distance de l'objectif doit être de 200m");
            assertEquals("00:03:20", objective.getTime(), "Le temps de l'objectif doit être 00:03:20");
        }
    }

    @Test
    void testCreateObjective() {
        User swimmer = userRepository.findById(6).orElseThrow();
        Swim swim = swimRepository.findById(4).orElseThrow();

        Objective objective = new Objective(
                5,
                swimmer,
                swim,
                "400m",
                "00:05:46"
        );

        objectiveRepository.save(objective);
        Optional<Objective> createdObjective = objectiveRepository.findById(objective.getId());

        assertNotNull(createdObjective, "L'objectif ne doit pas être null");
        assertTrue(createdObjective.get().getId() > 0);
        assertTrue(createdObjective.get().getSwimmer().getId() > 0);
        assertNotNull(createdObjective.get().getDistance());
        assertNotNull(createdObjective.get().getTime());
        assertEquals("400m", createdObjective.get().getDistance(), "La distance de l'objectif doit être de 400m");
        assertEquals("00:05:46", createdObjective.get().getTime(), "Le temps de l'objectif doit être 00:05:46");

    }
}

