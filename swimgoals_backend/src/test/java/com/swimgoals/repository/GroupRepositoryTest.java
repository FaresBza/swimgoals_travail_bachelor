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

    @Test
    void testFindAllGroups(){

        List<Group> groups = groupRepository.findAll();

        assertEquals(4, groups.size());
    }

    @Test
    void testFindGroupById(){

        Optional<Group> group = groupRepository.findById(1);

        assertTrue(group.isPresent(), "Group non trouvé");
    }

    @Test
    void testFindGroupByCoachId(){

        List<Group> listGroups = groupRepository.findByCoachId(2);
        assertEquals(4, listGroups.size());
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

        assertTrue(createdGroup.isPresent());
        assertEquals(group.getId(), createdGroup.get().getId());
        assertEquals("GroupTest", createdGroup.get().getName());
    }

    @Test
    void testDeleteGroup(){
        
        groupRepository.deleteById(3);
        
        Optional<Group> deletedGroup = groupRepository.findById(3);
        
        assertEquals(false, deletedGroup.isPresent());
    }

}