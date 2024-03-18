package com.lucid.oneiric.entities;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="dreams")
public class DreamEntity {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dream_kind")
    private DreamTypeEntity dreamKindEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dream_category_id")
    private DreamCategoryEntity dreamCategoryEntity;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "visibility_id")
    private VisibilityEntity visibilityEntity;

    @Column(name = "dream_title")
    private String dreamTitle;

    @Column(name = "dream_content")
    private String dreamContent;
    protected DreamEntity() {
    }



    public DreamEntity(UserEntity userEntity, DreamTypeEntity dreamKindEntity, DreamCategoryEntity dreamCategoryEntity, VisibilityEntity visibilityEntity, String dreamTitle, String dreamContent) {
        this.userEntity = userEntity;
        this.dreamKindEntity = dreamKindEntity;
        this.dreamCategoryEntity = dreamCategoryEntity;
        this.visibilityEntity = visibilityEntity;
        this.dreamTitle = dreamTitle;
        this.dreamContent = dreamContent;
        this.id = UUID.randomUUID().toString();
        this.creationDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public DreamTypeEntity getDreamKindEntity() {
        return dreamKindEntity;
    }

    public void setDreamKindEntity(DreamTypeEntity dreamKindEntity) {
        this.dreamKindEntity = dreamKindEntity;
    }

    public DreamCategoryEntity getDreamCategoryEntity() {
        return dreamCategoryEntity;
    }

    public void setDreamCategoryEntity(DreamCategoryEntity dreamCategoryEntity) {
        this.dreamCategoryEntity = dreamCategoryEntity;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public VisibilityEntity getVisibilityEntity() {
        return visibilityEntity;
    }

    public void setVisibilityEntity(VisibilityEntity visibilityEntity) {
        this.visibilityEntity = visibilityEntity;
    }

    public String getDreamTitle() {
        return dreamTitle;
    }

    public void setDreamTitle(String dreamTitle) {
        this.dreamTitle = dreamTitle;
    }

    public String getDreamContent() {
        return dreamContent;
    }

    public void setDreamContent(String dreamContent) {
        this.dreamContent = dreamContent;
    }
}
