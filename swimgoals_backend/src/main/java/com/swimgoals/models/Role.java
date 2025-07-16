package com.swimgoals.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    public Role() { 
        this(0, ""); 
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    
    public void setName(String name) { this.name = name; }


    public static final Role Admin = new Role(1, "admin");
    public static final Role Coach = new Role(2, "coach");
    public static final Role Swimmer = new Role(3, "swimmer");
}
