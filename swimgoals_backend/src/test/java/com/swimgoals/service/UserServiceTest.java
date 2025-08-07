package com.swimgoals.service;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import com.swimgoals.models.Role;
import com.swimgoals.models.User;
import com.swimgoals.repository.UserRepository;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    void testReturnAllUsers() {
        User user1 = new User(1, "John", "Doe", "john@doe.com", "123", new Role(1, "admin"));
        User user2 = new User(2, "Jean", "Dupont", "jeandupont@gamil.com", "jd129", new Role(2, "coach"));
        User user3 = new User(3, "Eric", "Richard", "erichard@gamil.com", "eric96g", new Role(3, "swimmer"));

        when(userRepository.findAll()).thenReturn(List.of(user1, user2, user3));

        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(3).containsExactly(user1, user2, user3);
    }

    @Test
    void testReturnUserById() {
        User user2 = new User(2, "Jean", "Dupont", "jeandupont@gamil.com", "jd129", new Role(2, "coach"));

        when(userRepository.findById(2)).thenReturn(Optional.of(user2));

        User user = userRepository.findById(2).get();
        assertThat(user).isEqualTo(user2);
    }

    @Test
    void testReturnUserByEmail() {
        User user3 = new User(3, "Eric", "Richard", "erichard@gamil.com", "eric96g", new Role(3, "swimmer"));

        when(userRepository.findById(3)).thenReturn(Optional.of(user3));

        User user = userRepository.findById(3).get();
        assertThat(user).isEqualTo(user3);
    }

    @Test
    void testReturnUserByEmailAndPassword() {
        User user4 = new User(4, "Smith", "Alice", "alice.smith@example.com", "securepass456", new Role(3, "swimmer"));

        when(userRepository.findById(4)).thenReturn(Optional.of(user4));

        User user = userRepository.findById(4).get();
        assertThat(user.getEmail()).isEqualTo(user4.getEmail());
        assertThat(user.getPassword()).isEqualTo(user4.getPassword());
    }
}