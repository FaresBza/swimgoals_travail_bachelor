package com.swimgoals.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GoalDTO {
    @JsonProperty("objectiveId")
    private Integer objectiveId;

    @JsonProperty("time")
    private String time;

    @JsonProperty("date")
    private String date;

    public GoalDTO() {}

    public Integer getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Integer objectiveId) {
        this.objectiveId = objectiveId;
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
