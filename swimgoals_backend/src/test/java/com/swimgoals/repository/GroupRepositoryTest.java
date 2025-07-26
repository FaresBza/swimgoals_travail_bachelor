package com.swimgoals.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.swimgoals.models.Group;
import com.swimgoals.models.Role;
import com.swimgoals.models.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAllGroups(){

        List<Group> groups = groupRepository.findAll();
        assertEquals(5, groups.size(), "Il devrait y avoir 5 groupes");

        for (Group group : groups) {
            assertTrue(group.getId() > 0);
            assertTrue(group.getName() != null && !group.getName().isEmpty(), "le name du grouoe ne doit pas être vide");
        }
    }

    @Test
    void testFindGroupById(){

        Optional<Group> group = groupRepository.findById(1);
        assertTrue(group.isPresent(), "Group non trouvé");
        assertEquals(2, group.get().getCoach().getId());
        assertEquals("Alice", group.get().getCoach().getFirstname());
        assertEquals("Smith", group.get().getCoach().getLastname());
    }

    @Test
    void testFindGroupByCoachId(){

        List<Group> listGroups = groupRepository.findByCoachId(3);
        assertEquals(3, listGroups.size());
        for (Group group : listGroups) {
            assertTrue(group.getId() > 0);
            assertTrue(group.getCoach().getId() > 0);
            assertEquals("Martin", group.getCoach().getFirstname());
            assertEquals("Dupont", group.getCoach().getLastname());
        }
    }

    @Test
    void testCreateGroup() {
        User coach = userRepository.findById(2).orElseThrow();
        Group group = new Group(coach, 4, "GroupTest", List.of());
        groupRepository.saveAndFlush(group);

        Optional<Group> createdGroup = groupRepository.findById(group.getId());

        assertTrue(createdGroup.isPresent());
        assertEquals("GroupTest", createdGroup.get().getName());
        assertEquals(2, createdGroup.get().getCoach().getId());
    }


}