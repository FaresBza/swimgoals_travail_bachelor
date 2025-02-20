package com.swimgoals.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Objective {
    
    @Id
    @Column(length = 16, nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "swimmer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_objective_swimmer"))
    private User swimmer;

    @ManyToOne
    @JoinColumn(name = "swim_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_objective_swim"))
    private Swim swim;

    @Column(name = "distance", nullable = false)
    private String distance;

    @Column(name = "time", nullable = false)
    private String time;

    

    public Objective(UUID id, User swimmer, Swim swim, String distance, String time) {
        this.id = id;
        this.swimmer = swimmer;
        this.swim = swim;
        this.distance = distance;
        this.time = time;
    }

    public Objective() {
        this(UUID.randomUUID(), null, null, "", "");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getSwimmer() {
        return swimmer;
    }

    public void setSwimmer(User swimmer) {
        this.swimmer = swimmer;
    }

    public Swim getSwim() {
        return swim;
    }

    public void setSwim(Swim swim) {
        this.swim = swim;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
