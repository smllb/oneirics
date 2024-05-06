package com.lucid.oneiric.dto;

import jakarta.validation.constraints.NotBlank;

public class UserNewDreamDTO {

    @NotBlank
    private String dreamTitle;
    @NotBlank
    private String dreamContent;
    @NotBlank
    private Integer dreamKind;

    @NotBlank
    private Integer dreamCategory;

    @NotBlank
    private Integer visibilityId;


    public UserNewDreamDTO() {

    }

    public UserNewDreamDTO(String dreamTitle, String dreamContent, Integer dreamKind, Integer dreamCategory, Integer visibilityId) {
        this.dreamTitle = dreamTitle;
        this.dreamContent = dreamContent;
        this.dreamKind = dreamKind;
        this.dreamCategory = dreamCategory;
        this.visibilityId = visibilityId;
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

    public Integer getDreamCategory() {
        return dreamCategory;
    }

    public void setDreamCategory(Integer dreamCategory) {
        this.dreamCategory = dreamCategory;
    }

    public Integer getVisibilityId() {
        return visibilityId;
    }

    public void setVisibilityId(Integer visibilityId) {
        this.visibilityId = visibilityId;
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
