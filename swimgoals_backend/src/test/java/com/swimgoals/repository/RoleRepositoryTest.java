package com.swimgoals.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.swimgoals.models.Role;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Important pour H2
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void testFindAllRoles() {
        List<Role> roles = roleRepository.findAll();

        assertEquals(3, roles.size());

        assertEquals("admin", roles.get(0).getName(), "Le premier rôle doit être 'admin'");
        assertEquals("coach", roles.get(1).getName(), "Le deuxième rôle doit être 'admin'");
        assertEquals("swimmer", roles.get(2).getName(), "Le troisième rôle doit être 'swimmer'");

        assertEquals(1, roles.get(0).getId(), "L'id du premier rôle doit être 1");
        assertEquals(2, roles.get(1).getId(), "L'id du deuxième rôle doit être 2");
        assertEquals(3, roles.get(2).getId(), "L'id du troisième rôle doit être 3");
    }

    @Test
    void testFindRoleById() {
        Optional<Role> role = roleRepository.findById(1);

        assertEquals(1, role.get().getId(), "L'id du rôle doit être 1");
        assertEquals("admin", role.get().getName(), "Le nom du rôle doit être 'admin'");
    }

    @Test
    void testCreateNewRole() {
        Role newRole = new Role(4, "test");
        roleRepository.save(newRole);

        Optional<Role> createdRole = roleRepository.findById(4);

        assertTrue(createdRole.isPresent(), "Le rôle 'guest' doit être inséré");

        assertEquals(4, createdRole.get().getId(), "L'ID du rôle 'test' doit être 4");
        assertEquals("test", createdRole.get().getName(), "Le nom du rôle inséré doit être 'test'");
    }
}
