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

import com.swimgoals.models.Role;
import com.swimgoals.models.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindAllUsers() {
        List<User> users = userRepository.findAll();
        
        assertEquals(5, users.size());
    }

    @Test
    void testFindUserById(){
        Optional<User> user = userRepository.findById(1);

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

        assertEquals(4, user.getId(), "L'id du user doit être 4");
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

        assertTrue(createdUser.isPresent(), "Le nouvel user doit être insérer");
        assertEquals(6, createdUser.get().getId(), "L'id du nouveau user doit être 6");
        assertEquals("Dupont", createdUser.get().getLastname(), "Le lastname du nouveau user doit être 'Dupont'");
        assertEquals("Jean", createdUser.get().getFirstname(), "Le firstname du nouveau user doit être 'Jean'");
        assertEquals("jean.dupont@gmail.com", createdUser.get().getEmail(), "L'email du nouveau user doit être 'jean.dupont@gmail.com'");
        assertEquals("123jean45", createdUser.get().getPassword(), "Le mot de passe du nouveau user doit être 'Du123jean45pont'");
        assertEquals("coach", createdUser.get().getRole().getName(), "Le role du nouveau user doit être 'coach'");
    }

    @Test
    void updateUser() {
        Optional<User> user = userRepository.findById(2);
        user.get().setPassword("newpassword123jean45");

        User updatedUser = userRepository.save(user.get());

        assertEquals("newpassword123jean45", updatedUser.getPassword(), "Le nouveau mot de passe du user doit être 'newpassword123jean45'");
    }

    @Test
    void deleteUser() {
        userRepository.deleteById(3);

        Optional<User> deletedUser = userRepository.findById(3);

        assertEquals(false, deletedUser.isPresent(), "le user d'id 3 doit être supprimé");
    }
}
