package com.swimgoals.service;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import org.junit.jupiter.api.Test;
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

    @Test
    void testReturnAllGroups() {
        Role roleCoach = new Role(2, "coach");
        User user = new User(
                1,
                "Dupont",
                "Jean",
                "jeandpt@gmail.com",
                "testpwd123",
                roleCoach
        );
        Group group1 = new Group(1, user, "Group1");
        Group group2 = new Group(2, user, "Group2");
        Group group3 = new Group(3, user, "Group3");

        when(groupRepository.findAll()).thenReturn(List.of(group1, group2, group3));

        List<Group> groups = groupRepository.findAll();

        assertThat(groups).hasSize(3).containsExactly(group1, group2, group3);
    }

    @Test
    void testReturnGroupById() {
        Role roleCoach = new Role(2, "coach");
        User user = new User(
                1,
                "Dupont",
                "Jean",
                "jeandpt@gmail.com",
                "testpwd123",
                roleCoach
        );
        Group group1 = new Group(1, user, "Group1");

        when(groupRepository.findById(1)).thenReturn(Optional.of(group1));

        Group group = groupRepository.findById(1).get();
        assertThat(group).isEqualTo(group1);
    }

    @Test
    void testReturnGroupByCoachId() {
        Role roleCoach = new Role(2, "coach");
        User user = new User(
                1,
                "Dupont",
                "Jean",
                "jeandpt@gmail.com",
                "testpwd123",
                roleCoach
        );
        Group group4 = new Group(4, user, "Group4");

        when(groupRepository.findByCoachId(1)).thenReturn(List.of(group4));

        List<Group> group = groupRepository.findByCoachId(1);
        assertThat(group.getFirst().getCoach().getId()).isEqualTo(group4.getCoach().getId());
    }
}