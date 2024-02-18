package com.lucid.oneiric.repository;

import com.lucid.oneiric.entities.DreamEntity;
import com.lucid.oneiric.entities.RoleEntity;
import lombok.NonNull;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DreamRepository extends JpaRepository<DreamEntity, String> {
    @NonNull
    @Override
    public Optional<DreamEntity> findById(@NonNull  String id);

    public List<DreamEntity> findAllByAuthorId(@NonNull String id);


    public List<DreamEntity> findAllByAuthorIdAndCreationDate(@NonNull String authorId, @NonNull LocalDate creationDate);

    public List<DreamEntity> findAllByAuthorIdAndVisibilityId(@NonNull String authorId, @NonNull Integer visibilityId);

    public List<DreamEntity> findAllByVisibilityId(@NonNull Integer visibilityId);

    public List<DreamEntity> findAllByDreamTitleLikeIgnoreCase(@NonNull String dreamTitle);

}