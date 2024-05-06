package com.lucid.oneiric.repository;

import com.lucid.oneiric.entities.DreamEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DreamRepository extends JpaRepository<DreamEntity, String> {
    @NonNull
    @Override
    Optional<DreamEntity> findById(@NonNull  String id);

    List<DreamEntity> findAllByCreationDateBetween(LocalDateTime start, LocalDateTime end);

     DreamEntity findByVisibilityEntityId(Integer visibilityId);

     List<DreamEntity> findAllByVisibilityEntityId(Integer visibilityId, Pageable page);

     Optional<List<DreamEntity>> findAllByUserEntityLoginAndVisibilityEntityId(String userEntity_login, Integer visibilityEntity_id);

     List<DreamEntity> findAllByUserEntityLogin(String userEntity_login);

     List<DreamEntity> findAllByDreamCategoryEntityId(Integer dreamCategoryEntity_id);

     void deleteById(@NonNull String id);

     void deleteAllByUserEntityLogin(@NonNull String userEntity_login);








    // i'll see what i do later.if i don't get a better idea, i'll just fill DreamEntities and convert them into the needed DTO but i feel like filling the all entities inside a dreamentity all the time is not very performative

}