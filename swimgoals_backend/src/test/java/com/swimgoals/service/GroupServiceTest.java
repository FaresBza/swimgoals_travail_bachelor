package com.swimgoals.service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.swimgoals.dto.GroupDTO;
import com.swimgoals.models.Group;
import com.swimgoals.models.Role;
import com.swimgoals.models.User;
import com.swimgoals.repository.GroupRepository;
import com.swimgoals.repository.UserRepository;
import com.swimgoals.service.impl.GroupServiceImpl;

@SpringBootTest
class GroupServiceTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

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

    @Test
    void shouldCreateGroupSuccessfully() {
        User coach = getCoachUser();
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName("Test Group");
        groupDTO.setCoachId(coach.getId());

        Group createdGroup = new Group(coach, 1, "Test Group", List.of());

        when(userRepository.findById(1)).thenReturn(Optional.of(coach));
        when(groupRepository.save(any(Group.class))).thenReturn(createdGroup);

        Group result = groupServiceImpl.createGroup(groupDTO);

        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getName()).isEqualTo("Test Group");
        assertThat(result.getCoach()).isEqualTo(coach);

        verify(userRepository).findById(1);
        verify(groupRepository).save(any(Group.class));
    }

    @Test
    void shouldThrowExceptionWhenCoachDoesNotExist() {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setName("Test Group");
        groupDTO.setCoachId(10000);

        when(userRepository.findById(10000)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> groupServiceImpl.createGroup(groupDTO))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Coach with the given ID does not exist");

        verify(userRepository).findById(10000);
        verifyNoInteractions(groupRepository);
    }


}
