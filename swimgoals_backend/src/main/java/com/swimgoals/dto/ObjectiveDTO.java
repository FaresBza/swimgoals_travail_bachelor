package com.swimgoals.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectiveDTO {
    @JsonProperty("swimId")
    private Integer swimId;

    @JsonProperty("swimmerId")
    private Integer swimmerId;

    @JsonProperty("distance")
    private String distance;

    @JsonProperty("time")
    private String time;

    public ObjectiveDTO() {}

    public Integer getSwimId() { return swimId; }

    public void setSwimId(Integer swimId) { this.swimId = swimId; }

    public Integer getSwimmerId() { return swimmerId; }

    public void setSwimmerId(Integer swimmerId) { this.swimmerId = swimmerId; }

    public String getDistance() { return distance; }

    public void setDistance(String distance) { this.distance = distance; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }
}
