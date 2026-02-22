package com.midominio.group.app.spring.dto;

public class ExternalActivityDTO {

    private String activity;
    private String type;
    private Integer participants;

    public ExternalActivityDTO() {
    }

    public ExternalActivityDTO(String activity, String type, Integer participants) {
        this.activity = activity;
        this.type = type;
        this.participants = participants;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }
}