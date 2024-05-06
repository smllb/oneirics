package com.lucid.oneiric.repository;

import com.lucid.oneiric.entities.DreamTypeEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DreamTypeRepository extends JpaRepository<DreamTypeEntity, Integer> {

    @NonNull
    @Override
    public Optional<DreamTypeEntity> findById(@NonNull Integer id);

    public DreamTypeEntity findByKind(String name);

}
