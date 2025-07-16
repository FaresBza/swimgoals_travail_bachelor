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
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 16, nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "objective_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_goal_objective"))
    private Objective objective;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "date", nullable = false)
    private String date;

    public Goal() { 
        this(0, null, "", ""); 
    }

    public Goal(Integer id, Objective objective, String time, String date) {
        this.id = id;
        this.objective = objective;
        this.time = time;
        this.date = date;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Objective getObjective() { return objective; }

    public void setObjective(Objective objective) { this.objective = objective; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}
