package com.swimgoals.repository;

import com.swimgoals.models.Objective;
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
public class ObjectiveRepositoryTest {

    @Autowired
    private ObjectiveRepository objectiveRepository;

    @Autowired
    private UserRepository userRepository;

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
}

