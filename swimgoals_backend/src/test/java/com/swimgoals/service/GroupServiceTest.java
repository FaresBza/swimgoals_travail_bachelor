package com.swimgoals.service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import com.swimgoals.service.impl.GroupServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.swimgoals.models.Group;
import com.swimgoals.models.Role;
import com.swimgoals.models.User;
import com.swimgoals.repository.GroupRepository;

@SpringBootTest
class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupServiceImpl;

    private User getCoachUser() {
        Role coachRole = new Role(2, "coach");
        return new User(
                1,
                "Dupont",
                "Jean",
                "jeandpt@gmail.com",
                "testpwd123",
                coachRole
        );
    }

    @Test
    void shouldReturnAllGroups() {
        User coach = getCoachUser();

        Group g1 = new Group(coach, 1, "Group 1", List.of());
        Group g2 = new Group(coach, 2, "Group 2", List.of());

        when(groupRepository.findAll()).thenReturn(List.of(g1, g2));

        List<Group> groups = groupServiceImpl.getAllGroups();

        assertThat(groups).hasSize(2).containsExactly(g1, g2);
    }

    @Test
    void shouldReturnGroupById() {
        User coach = getCoachUser();
        Group g = new Group(coach, 1, "Group 1", List.of());

        when(groupRepository.findById(1)).thenReturn(Optional.of(g));

        Optional<Group> result = groupServiceImpl.getGroupById(1);

        assertThat(result).isPresent().contains(g);
    }

    @Test
    void shouldReturnGroupsByCoachId() {
        User coach = getCoachUser();
        Group g = new Group(coach, 4, "Masters Group", List.of());

        when(groupRepository.findByCoachId(1)).thenReturn(List.of(g));

        List<Group> groups = groupServiceImpl.getGroupsByCoachId(1);

        assertThat(groups).hasSize(1).containsExactly(g);
    }
}
