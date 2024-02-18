package com.lucid.oneiric.dto;

import com.lucid.oneiric.entities.DreamEntity;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class DreamDTO {
    private String dreamId;
    private String authorId;
    private Integer dreamKind;
    private Integer dreamCategoryId;
    private LocalDateTime creationDate;
    private Integer visibilityId;
    private String dreamTitle;
    private String dreamContent;

    public DreamDTO(String dreamId) {

        this.dreamId = dreamId;
    }

    public DreamDTO(String dreamId, String authorId, Integer dreamKind, Integer dreamCategoryId, LocalDateTime creationDate, Integer visibilityId, String dreamTitle, String dreamContent) {
        this.dreamId = dreamId;
        this.authorId = authorId;
        this.dreamKind = dreamKind;
        this.dreamCategoryId = dreamCategoryId;
        this.creationDate = creationDate;
        this.visibilityId = visibilityId;
        this.dreamTitle = dreamTitle;
        this.dreamContent = dreamContent;
    }

    public DreamDTO() {

    }

    public String getDreamId() {
        return dreamId;
    }

    public void setDreamId(String dreamId) {
        this.dreamId = dreamId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public Integer getDreamKind() {
        return dreamKind;
    }

    public Integer getDreamCategoryId() {
        return dreamCategoryId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getVisibilityId() {
        return visibilityId;
    }

    public String getDreamTitle() {
        return dreamTitle;
    }

    public String getDreamContent() {
        return dreamContent;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setDreamKind(Integer dreamKind) {
        this.dreamKind = dreamKind;
    }

    public void setDreamCategoryId(Integer dreamCategoryId) {
        this.dreamCategoryId = dreamCategoryId;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setVisibilityId(Integer visibilityId) {
        this.visibilityId = visibilityId;
    }

    public void setDreamTitle(String dreamTitle) {
        this.dreamTitle = dreamTitle;
    }

    public void setDreamContent(String dreamContent) {
        this.dreamContent = dreamContent;
    }
}
