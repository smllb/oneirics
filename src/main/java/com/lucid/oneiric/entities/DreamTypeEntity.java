package com.lucid.oneiric.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "dream_types")
public class DreamTypeEntity {
    @Id
    private Integer id;
    private String kind;

    protected DreamTypeEntity() {

    }

    public DreamTypeEntity(Integer id, String kind) {
        this.id = id;
        this.kind = kind;
    }

    public Integer getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
