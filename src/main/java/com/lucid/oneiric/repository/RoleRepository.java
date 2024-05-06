package com.lucid.oneiric.repository;

import com.lucid.oneiric.entities.RoleEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    @NonNull
    Optional<RoleEntity> findById(@NonNull Integer id);

    RoleEntity findByName(@NonNull String name);
}
