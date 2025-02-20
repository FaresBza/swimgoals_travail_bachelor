package com.swimgoals.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "swimmers_group")
public class SwimmersGroup {
    
    @Id
    @ManyToOne
    @JoinColumn(name = "swimmer_id", foreignKey = @ForeignKey(name = "fk_swimmer_id"))
    private User swimmer;

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "fk_group_id"))
    private Group group;

    public SwimmersGroup(User swimmer, Group group) {
        this.swimmer = swimmer;
        this.group = group;
    }

    public SwimmersGroup() {
        this(null, null);
    }

    public User getSwimmer() {
        return swimmer;
    }

    public void setSwimmer(User swimmer) {
        this.swimmer = swimmer;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
