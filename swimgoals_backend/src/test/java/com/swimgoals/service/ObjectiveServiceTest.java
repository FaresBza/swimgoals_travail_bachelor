package com.swimgoals.service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.swimgoals.models.Objective;
import com.swimgoals.models.Role;
import com.swimgoals.models.Swim;
import com.swimgoals.models.User;
import com.swimgoals.repository.ObjectiveRepository;
import com.swimgoals.service.impl.ObjectiveServiceImpl;

@SpringBootTest
class ObjectiveServiceTest {

    @Mock
    private ObjectiveRepository objectiveRepository;

    @InjectMocks
    private ObjectiveServiceImpl objectiveService;

    private User getSwimmerUser() {
        Role swimmerRole = new Role(3, "swimmer");
        return new User(
                1,
                "Richard",
                "David",
                "drichars@gmail.com",
                "password152",
                swimmerRole
        );
    }

    private Swim getSwimmerSwimmer() {
        return new Swim(1, "Pappillon" );
    }

    @Test
    void shouldReturnAllObjectives() {
        User user = getSwimmerUser();
        Swim swim = getSwimmerSwimmer();

        Objective obj1 = new Objective(1, user, swim,"50m", "00:01:38");
        Objective obj2 = new Objective(2, user, swim,"200m", "00:04:31");

        when(objectiveRepository.findAll()).thenReturn(List.of(obj1, obj2));

        List<Objective> objectives = objectiveService.getAllObjectives();

        assertThat(objectives).hasSize(2).containsExactly(obj1, obj2);
    }

    @Test
    void shouldReturnObjectiveById() {
        User user = getSwimmerUser();
        Swim swim = getSwimmerSwimmer();

        Objective objective = new Objective(1, user, swim, "50m", "00:01:38");

        when(objectiveRepository.findById(1)).thenReturn(Optional.of(objective));

        Optional<Objective> result = objectiveService.getObjectiveById(1);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(1);
        assertThat(result.get().getId()).isEqualTo(user.getId());
        assertThat(result.get().getDistance()).isEqualTo("50m");
        assertThat(result.get().getTime()).isEqualTo("00:01:38");
    }
}