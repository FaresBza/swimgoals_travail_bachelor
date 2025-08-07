package com.swimgoals.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.swimgoals.models.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.swimgoals.models.Role;
import com.swimgoals.models.User;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    void testFindAllUsers() {
        List<User> users = userRepository.findAll();
        
        assertEquals(6, users.size(), "Le nombre de groupes doit être égal à 6");
        assertInstanceOf(User.class, users.get(0), "le 1er user doit être une instance de User");
        assertFalse(users.isEmpty(), "Le liste des users ne doit pas être vide");

        assertEquals("Doe", users.get(0).getLastname(), "Le lastname du 1er user doit être 'Doe'");
        assertEquals("Michael", users.get(3).getFirstname(), "Le firstname du 4ème user doit être 'Michael'");
        assertEquals("emily.johnson@example.com", users.get(4).getEmail(), "L'email du 5ème user doit être 'emily.johnson@example.com'");
        assertEquals("david.williams@example.com", users.get(5).getEmail(), "L'email du 6ème user doit être 'david.williams@example.com'");
        assertEquals("securepass456", users.get(1).getPassword(), "Le mot de passe du 2pme user doit être 'securepass456'");

        assertEquals("admin", users.get(0).getRole().getName(), "Le role du 1er user doit être 'admin'");
        assertEquals("coach", users.get(1).getRole().getName(), "Le role du 2ème user doit être 'coach'");
        assertEquals("coach", users.get(2).getRole().getName(), "Le role du 3ème user doit être 'coach'");
        assertEquals("swimmer", users.get(3).getRole().getName(), "Le role du 4ème user doit être 'swimmer'");

    }

    @Test
    void testFindUserById(){
        Optional<User> user = userRepository.findById(1);

        assertNotNull(user, "Le user ne doit pas être null");
        assertTrue(user.isPresent(), "Le user doit exister");
        assertFalse(user.isEmpty(), "Le user ne doit pas être vide");

        assertEquals(1, user.get().getId(), "L'id du user doit être 1");
        assertEquals("Doe", user.get().getLastname(), "Le lastname du user doit être 'Doe'");
        assertEquals("John", user.get().getFirstname(), "Le firstname du user doit être 'John'");
        assertEquals("john.doe@example.com", user.get().getEmail(), "L'email du user doit être 'john.doe@example.com'");
        assertEquals("password123", user.get().getPassword(), "Le mot de passe du user doit être 'password123'");
        assertEquals(1, user.get().getRole().getId(), "Le role_id du user doit être 1");
    }

    @Test
    void findUserByEmail(){
        User user = userRepository.findByEmail("emily.johnson@example.com");

        assertNotNull(user);
        assertInstanceOf(User.class, user, "le user doit être une instance de User");

        assertEquals(5, user.getId(), "L'id du user doit être 5");
        assertEquals("Johnson", user.getLastname(), "Le lastname du user doit être 'Johnson'");
        assertEquals("Emily", user.getFirstname(), "Le firstname du user doit être 'Emily'");
        assertEquals("emily.johnson@example.com", user.getEmail(), "L'email du user doit être 'emily.johnson@example.com'");
        assertEquals("strongpass321", user.getPassword(), "Le mot de passe du user doit être 'strongpass321'");
        assertEquals(3, user.getRole().getId(), "Le role_id du user doit être 3");
    }

    @Test
    void createUser(){
        User newUser = new User(
            6,
            "Dupont", 
            "Jean", 
            "jean.dupont@gmail.com", 
            "123jean45", 
            new Role(
                2, 
                "coach"
            ));
        userRepository.save(newUser);

        Optional<User> createdUser = userRepository.findById(newUser.getId());

        assertNotNull(createdUser, "Le nouveau user ne doit pas être null");
        assertTrue(!createdUser.isEmpty(), "Le nouveau utilisateur n'est pas null");

        assertTrue(createdUser.isPresent(), "Le nouvel user doit être insérer");
        assertEquals(6, createdUser.get().getId(), "L'id du nouveau user doit être 6");
        assertEquals("Dupont", createdUser.get().getLastname(), "Le lastname du nouveau user doit être 'Dupont'");
        assertEquals("Jean", createdUser.get().getFirstname(), "Le firstname du nouveau user doit être 'Jean'");
        assertEquals("jean.dupont@gmail.com", createdUser.get().getEmail(), "L'email du nouveau user doit être 'jean.dupont@gmail.com'");
        assertEquals("123jean45", createdUser.get().getPassword(), "Le mot de passe du nouveau user doit être 'Du123jean45pont'");
        assertEquals("coach", createdUser.get().getRole().getName(), "Le role du nouveau user doit être 'coach'");
    }

    @Test
    void testUpdateUserInfo() {
        User user = userRepository.findById(2).orElseThrow();
        user.setFirstname("Anne");
        user.setLastname("Richard");
        user.setEmail("anne.richard@gmail.com");
        user.setPassword("arichard12");
        userRepository.save(user);

        Optional<User> updatedUser = userRepository.findById(2);

        assertEquals("Anne", updatedUser.get().getFirstname(), "Le nouveau prénom du user doit être 'Anne'");
        assertEquals("Richard", updatedUser.get().getLastname(), "Le nouveau nom du user doit être 'Richard'");
        assertEquals("anne.richard@gmail.com", updatedUser.get().getEmail(), "Le nouvel email du user doit être 'anne.richard@gmail.com'");
        assertEquals("arichard12", updatedUser.get().getPassword(), "Le nouveau mot de passe du user doit être 'arichard12'");
    }

    @Test
    void testUpdateUserRole() {
        User user = userRepository.findById(6).orElseThrow();
        Role newRole = roleRepository.findById(2).orElseThrow();

        user.setRole(newRole);
        userRepository.save(user);

        Optional<User> updatedUserRole = userRepository.findById(6);
        assertEquals("coach", updatedUserRole.get().getRole().getName(), "Le nouveau rôle du user doit être 'coach'");
    }

    @Test
    void testUpdateUserGroup() {
        User user = userRepository.findById(4).orElseThrow();
        Group newGroup = groupRepository.findById(2).orElseThrow();

        user.setGroup(newGroup);
        userRepository.save(user);

        Optional<User> updatedUser = userRepository.findById(4);
        assertEquals("Group2", updatedUser.get().getGroup().getName(), "Le nouveau groupe du user doit être 'Group2'");
    }
}
