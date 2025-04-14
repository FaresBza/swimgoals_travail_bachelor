package com.swimgoals.dto;

public class GroupDTO {
    private int coachId;
    private String name;

    // Constructeurs
    public GroupDTO() {}

    public GroupDTO(int coachId, String name) {
        this.coachId = coachId;
        this.name = name;
    }

    // Getters et setters
    public int getCoachId() {
        return coachId;
    }

    public void setCoachId(int coachId) {
        this.coachId = coachId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
