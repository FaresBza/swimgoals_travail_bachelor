package com.swimgoals.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.swimgoals.models.Group;
import com.swimgoals.models.Role;
import com.swimgoals.models.User;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class GroupRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void testFindAllGroups(){
        List<Group> groups = groupRepository.findAll();

        assertEquals(5, groups.size(), "Le nombre de groupes doit être égal à 5");
        assertFalse(groups.isEmpty(), "La liste des groupes ne doit pas être vide");

        assertNotNull(groups.get(3).getCoach(), "le groupe d'id 4 (Group4) doit avoir un coach");

        assertEquals(3, groups.get(1).getCoach().getId(), "Le groupe d'id 1 (Group1) doit être du coach d'id 3 (Martin)");
        assertEquals(2, groups.get(2).getCoach().getId(), "Le groupe d'id 3 (Group1) doit être du coach d'id 2 (Alice)");
        assertEquals(3, groups.get(4).getCoach().getId(), "Le groupe d'id 5 (Group1) doit être du coach d'id 3 (Martin)");

        assertEquals("Group1", groups.get(0).getName(), "le groupe d'id 1 doit être 'Group1'");
        assertEquals("Group3", groups.get(2).getName(), "le groupe d'id 3 doit être 'Group3'");
        assertEquals("Group5", groups.get(4).getName(), "le groupe d'id 5 doit être 'Group5'");
    }

    @Test
    void testFindGroupById(){
        Optional<Group> group = groupRepository.findById(1);

        assertTrue(group.isPresent(), "Group non trouvé");
        assertEquals("Group2", group.get().getName(), "Le name du groupe doit être 'Group2");
    }

    @Test
    void testFindGroupByCoachId(){
        List<Group> listGroups = groupRepository.findByCoachId(2);

        assertEquals(2, listGroups.size(), "Le nombre de groupes doit être égal à 2");
        assertFalse(listGroups.isEmpty(), "La liste group ne doit pas être vide");
        assertNotNull(listGroups.get(1).getCoach(), "le groupe d'id 2 (Group2) doit avoir un coach");
    }

    @Test
    void testCreateGroup(){
        User user = new User(
                2,
                "Smith",
                "Alice",
                "alice.smith@example.com",
                "securepass456",
                new Role(2, "coach")
        );
        Group group = new Group(4, user, "GroupTest");
        groupRepository.save(group);

        Optional<Group> createdGroup = groupRepository.findById(group.getId());

        assertTrue(createdGroup.isPresent(), "Le groupe doit être créer");
        assertEquals(group.getId(), createdGroup.get().getId());
        assertEquals("GroupTest", createdGroup.get().getName(), "Le name du nouveau groupe créer doit être 'GroupTest'");
    }

    @Test
    void testDeleteGroup(){
        groupRepository.deleteById(3);
        
        Optional<Group> deletedGroup = groupRepository.findById(3);

        assertFalse(deletedGroup.isPresent(), "Le groupe doit être supprimé");
    }

}