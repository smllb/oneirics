package com.lucid.oneiric.repository;

import com.lucid.oneiric.entities.DreamCategoryEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface DreamCategoryRepository extends JpaRepository<DreamCategoryEntity, Integer> {

    @NonNull
    @Override
    public Optional<DreamCategoryEntity> findById(@NonNull Integer id);

    public DreamCategoryEntity findByCategory(String category);

}
