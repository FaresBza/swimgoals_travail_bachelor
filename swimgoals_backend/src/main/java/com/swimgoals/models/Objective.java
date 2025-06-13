package com.swimgoals.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "objectives")
public class Objective {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 16, nullable = false, unique = true)
    private Integer id;

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

    // @OneToMany(mappedBy = "objective")
    // @JsonIgnore
    // private List<Goal> goals;

    public Objective(Integer id, User swimmer, Swim swim, String distance, String time) {
        this.id = id;
        this.swimmer = swimmer;
        this.swim = swim;
        this.distance = distance;
        this.time = time;
    }

    public Objective() {
        this(0, null, null, "", "");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    // public List<Goal> getGoals() {
    //     return goals;
    // }

    // public void setGoals(List<Goal> goals) {
    //     this.goals = goals;
    // }
}
