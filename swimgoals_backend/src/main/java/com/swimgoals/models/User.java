package com.swimgoals.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    
    private Integer id;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public User() {
        this(0, "", "", "", "", null);
    }

    public User(Integer id, String lastname, String firstname, String email, String password, Role role) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public Group getGroup() { return group; }

    public void setGroup(Group group) { this.group = group; }
}
