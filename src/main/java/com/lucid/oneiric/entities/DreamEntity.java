package com.lucid.oneiric.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="dreams")
public class DreamEntity {
    @Id
    private String id;

    @Column(name = "author_id")
    private String authorId;
    @Column(name = "dream_kind")
    private Integer dreamKind;
    @Column(name = "dream_category_id")
    private Integer dreamCategoryId;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @Column(name = "visibility_id")
    private Integer visibilityId;

    @Column(name = "dream_title")
    private String dreamTitle;

    @Column(name = "dream_content")
    private String dreamContent;
    protected DreamEntity() {
    }

    public DreamEntity(String authorId, String dreamTitle, String dreamContent, Integer dreamKind, Integer dreamCategoryId, Integer visibilityId) {
        this.id = UUID.randomUUID().toString();
        this.authorId = authorId;
        this.dreamTitle = dreamTitle;
        this.dreamContent = dreamContent;
        this.dreamKind = dreamKind;
        this.dreamCategoryId = dreamCategoryId;
        this.creationDate = LocalDateTime.now();
        this.visibilityId = visibilityId;
    }

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setDreamKind(Integer dreamKind) {
        this.dreamKind = dreamKind;
    }

    public void setDreamCategory(Integer dreamCategoryId) {
        this.dreamCategoryId = dreamCategoryId;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setVisibilityId(Integer visibilityId) {
        this.visibilityId = visibilityId;
    }

    public String getDreamTitle() {
        return dreamTitle;
    }

    public String getDreamContent() {
        return dreamContent;
    }

    public void setDreamCategoryId(Integer dreamCategoryId) {
        this.dreamCategoryId = dreamCategoryId;
    }

    public void setDreamTitle(String dreamTitle) {
        this.dreamTitle = dreamTitle;
    }

}
