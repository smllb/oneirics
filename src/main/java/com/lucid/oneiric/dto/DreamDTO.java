package com.lucid.oneiric.dto;

import java.time.LocalDateTime;

public class DreamDTO {
    private String id;
    private LocalDateTime creationDate;
    private String authorName;
    private String dreamKind;
    private String dreamCategory;
    private String visibilityName;

    private String dreamContent;
    private String dreamTitle;

    public DreamDTO() {

    }

    public DreamDTO(String id, LocalDateTime creationDate, String authorName, String dreamKind, String dreamCategory, String visibilityName, String dreamContent, String dreamTitle) {
        this.id = id;
        this.creationDate = creationDate;
        this.authorName = authorName;
        this.dreamKind = dreamKind;
        this.dreamCategory = dreamCategory;
        this.visibilityName = visibilityName;
        this.dreamContent = dreamContent;
        this.dreamTitle = dreamTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDreamKind(String kind) {
        return dreamKind;
    }

    public void setDreamKind(String dreamKind) {
        this.dreamKind = dreamKind;
    }

    public String getDreamCategory() {
        return dreamCategory;
    }

    public void setDreamCategory(String dreamCategory) {
        this.dreamCategory = dreamCategory;
    }

    public String getVisibilityName() {
        return visibilityName;
    }

    public void setVisibilityName(String visibilityName) {
        this.visibilityName = visibilityName;
    }

    public String getDreamContent() {
        return dreamContent;
    }

    public void setDreamContent(String dreamContent) {
        this.dreamContent = dreamContent;
    }

    public String getDreamTitle() {
        return dreamTitle;
    }

    public void setDreamTitle(String dreamTitle) {
        this.dreamTitle = dreamTitle;
    }
}
