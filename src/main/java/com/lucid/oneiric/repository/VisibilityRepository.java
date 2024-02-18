package com.lucid.oneiric.repository;

import com.lucid.oneiric.entities.VisibilityEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisibilityRepository extends JpaRepository<VisibilityEntity, Integer> {

    @NonNull
    @Override
    public Optional<VisibilityEntity> findById(@NonNull Integer id);

    public VisibilityEntity findByName(String name);

}
