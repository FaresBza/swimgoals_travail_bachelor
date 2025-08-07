package com.swimgoals.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.swimgoals.models.Group;
import com.swimgoals.models.User;

import static org.junit.jupiter.api.Assertions.*;

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
            assertTrue(group.getName() != null && !group.getName().isEmpty(), "Le nom du groupe ne doit pas être vide");
        }
    }

    @Test
    void testFindGroupById(){
        Optional<Group> group = groupRepository.findById(1);
        assertTrue(group.isPresent(), "Group non trouvé");

        assertEquals(2, group.get().getCoach().getId());
        assertEquals("Alice", group.get().getCoach().getFirstname(), "Le prénom du coach doit être 'Alice'");
        assertEquals("Smith", group.get().getCoach().getLastname(), "Le nom du coach doit être 'Smith'");
    }

    @Test
    void testFindGroupByCoachId(){
        List<Group> listGroups = groupRepository.findByCoachId(3);
        assertEquals(3, listGroups.size(), "Le coach avec l'ID 3 doit avoir 3 groupes");

        for (Group group : listGroups) {
            assertTrue(group.getId() > 0);
            assertTrue(group.getCoach().getId() > 0);
            assertEquals("Martin", group.getCoach().getFirstname(), "Le prénom du coach doit être 'Martin'");
            assertEquals("Dupont", group.getCoach().getLastname(), "Le nom du coach doit être 'Dupont'");
        }
    }

    @Test
    void testCreateGroup() {
        User coach = userRepository.findById(2).orElseThrow();
        Group group = new Group(coach, 4, "GroupTest", List.of());
        groupRepository.saveAndFlush(group);

        Optional<Group> createdGroup = groupRepository.findById(group.getId());

        assertTrue(createdGroup.isPresent(), "Le groupe créé doit exister");
        assertEquals("GroupTest", createdGroup.get().getName(), "Le nom du groupe doit être 'GroupTest'");
        assertEquals(2, createdGroup.get().getCoach().getId(), "L'ID du coach assigné au groupe doit être 2");
    }

    @Test
    void testUpdateGroupInfo() {
        Group group = groupRepository.findById(1).orElseThrow();
        group.setName("Juniors");

        groupRepository.save(group);

        Optional<Group> updatedGroup = groupRepository.findById(group.getId());
        assertEquals("Juniors", updatedGroup.get().getName(), "Le nouveau nom du groupe doit être 'Juniors'");
    }

    @Test
    void testUpdateGroupCoach() {
        Group group = groupRepository.findById(2).orElseThrow();
        User coach = userRepository.findById(2).orElseThrow();
        group.setCoach(coach);

        groupRepository.save(group);

        Optional<Group> updatedGroup = groupRepository.findById(group.getId());
        assertEquals(2, updatedGroup.get().getCoach().getId(), "Le nouveau groupe doit appartenit au coach d'ID 2");
    }

    @Test
    void testDeleteGroup() {
        Group group = groupRepository.findById(5).orElseThrow();
        groupRepository.delete(group);
        Optional<Group> deletedGroup = groupRepository.findById(group.getId());
        assertTrue(deletedGroup.isEmpty(), "Le groupe doit avoir été supprimé de la base de données");
    }
}