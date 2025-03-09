package com.swimgoals.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    }

    @Test
    void testFindRoleById() {
        Optional<Role> role = roleRepository.findById(1);

        assertEquals("admin", role.get().getName());
    }

    @Test
    void testCreateNewRole() {
        Role newRole = new Role(4, "test");
        roleRepository.save(newRole);

        Optional<Role> createdRole = roleRepository.findById(4);

        assertEquals("test", createdRole.get().getName());
    }

    @Test 
    void testDeleteRole() {
        roleRepository.deleteById(2);

        Optional<Role> deletedRole = roleRepository.findById(2);

        assertEquals(false, deletedRole.isPresent());
    }
}
