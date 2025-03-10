package com.swimgoals.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

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
        System.out.println("Nombre d'utilisateurs en base : " + users.size());
        assertEquals(5, users.size());
    }

    @Test
    void testFindUserById(){
        Optional<User> user = userRepository.findById(1);
        assertEquals(1, user.get().getId());
    }
}
