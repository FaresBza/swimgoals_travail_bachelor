package com.swimgoals.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    private UUID id;
    private UUID coach;
    private String name;

    public Group(UUID id, UUID coach, String name) {
        this.id = id;
        this.coach = coach;
        this.name = name;
    }

    public Group() {
        this(UUID.randomUUID(), null, "");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCoach() {
        return coach;
    }

    public void setCoach(UUID coach) {
        this.coach = coach;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
