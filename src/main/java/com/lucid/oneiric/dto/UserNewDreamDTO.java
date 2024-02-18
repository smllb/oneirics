package com.lucid.oneiric.dto;

import jakarta.validation.constraints.NotBlank;

public class UserNewDreamDTO {

    @NotBlank
    private String dreamTitle;
    @NotBlank
    private String dreamContent;
    @NotBlank
    private Integer dreamKind;


    public UserNewDreamDTO() {

    }

    public UserNewDreamDTO(String dreamTitle, String dreamContent, Integer dreamKind) {
        this.dreamTitle = dreamTitle;
        this.dreamContent = dreamContent;
        this.dreamKind = dreamKind;
    }

    public String getDreamTitle() {
        return dreamTitle;
    }

    public String getDreamContent() {
        return dreamContent;
    }

    public Integer getDreamKind() {
        return dreamKind;
    }

    public void setDreamTitle(String dreamTitle) {
        this.dreamTitle = dreamTitle;
    }

    public void setDreamContent(String dreamContent) {
        this.dreamContent = dreamContent;
    }

    public void setDreamKind(Integer dreamKind) {
        this.dreamKind = dreamKind;
    }

    @Override
    public String toString() {
        return "UserNewDreamDTO{" +
                "dreamTitle='" + dreamTitle + '\'' +
                ", dreamContent='" + dreamContent + '\'' +
                ", dreamKind='" + dreamKind + '\'' +
                '}';
    }
}
