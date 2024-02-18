package com.lucid.oneiric.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dream_categories")
public class DreamCategoryEntity {
    @Id
    private Integer id;

    private String category;

    protected DreamCategoryEntity() {

    }

    public DreamCategoryEntity(Integer id, String category) {
        this.id = id;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public String getcategory() {
        return category;
    }
}
