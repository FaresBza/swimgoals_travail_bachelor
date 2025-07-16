package com.swimgoals.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GroupDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("coachId")
    private Integer coachId;

    public GroupDTO() {}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getCoachId() { return coachId; }

    public void setCoachId(Integer coachId) { this.coachId = coachId; }
}
