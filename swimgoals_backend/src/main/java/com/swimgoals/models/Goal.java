package com.swimgoals.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Goal {

    @Id
    @Column(length = 16, nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "objective_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_goal_objective"))
    private Objective objective;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "date", nullable = false)
    private String date;

    public Goal(UUID id, Objective objective, String time, String date) {
        this.id = id;
        this.objective = objective;
        this.time = time;
        this.date = date;
    }

    public Goal() {
        this(UUID.randomUUID(), null, "", "");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
