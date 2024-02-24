package com.lucid.oneiric.repository;

import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.entities.DreamEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DreamRepository extends JpaRepository<DreamEntity, String> {
    @NonNull
    @Override
    public Optional<DreamEntity> findById(@NonNull  String id);

//    public List<DreamEntity> findAllByAuthorId(@NonNull String id);

//    public List<DreamEntity> findAllByAuthorIdAndCreationDate(@NonNull String authorId, @NonNull LocalDate creationDate);
//
//    public List<DreamEntity> findAllByAuthorIdAndVisibilityId(@NonNull String authorId, @NonNull Integer visibilityId);
//
//    public List<DreamEntity> findAllByVisibilityId(@NonNull Integer visibilityId);

    public List<DreamEntity> findAllByDreamTitleLikeIgnoreCase(@NonNull String dreamTitle);

    @Query("SELECT new com.lucid.oneiric.dto.DreamDTO(d.id, d.creationDate, u.login, dt.kind, dc.category, v.name, d.dreamContent, d.dreamTitle) " +
            "FROM DreamEntity d " +
            "INNER JOIN d.userEntity u " +
            "INNER JOIN d.dreamKindEntity dt " +
            "INNER JOIN d.dreamCategoryEntity dc " +
            "INNER JOIN d.visibilityEntity v " +
            "WHERE d.id = :id")
    DreamDTO fillDreamDTOByDreamId(@Param("id") String id);

    @Query("SELECT new com.lucid.oneiric.dto.DreamDTO(d.id, d.creationDate, u.login, dt.kind, dc.category, v.name, d.dreamContent, d.dreamTitle) " +
            "FROM DreamEntity d " +
            "INNER JOIN d.userEntity u " +
            "INNER JOIN d.dreamKindEntity dt " +
            "INNER JOIN d.dreamCategoryEntity dc " +
            "INNER JOIN d.visibilityEntity v " +
            "WHERE v.id = :id")
    List<DreamDTO> fillDreamListByVisibility(@Param("id") String id);

    @Query("SELECT new com.lucid.oneiric.dto.DreamDTO(d.id, d.creationDate, u.login, dt.kind, dc.category, v.name, d.dreamContent, d.dreamTitle) " +
            "FROM DreamEntity d " +
            "INNER JOIN d.userEntity u " +
            "INNER JOIN d.dreamKindEntity dt " +
            "INNER JOIN d.dreamCategoryEntity dc " +
            "INNER JOIN d.visibilityEntity v " +
            "WHERE u.login = :username "
    )
    List<DreamDTO> fillDreamListByUsername(@Param("username") String username);

    // i'll see what i do later.if i don't get a better idea, i'll just fill DreamEntities and convert them into the needed DTO but i feel like filling the all entities inside a dreamentity all the time is not very performative

}