package com.lucid.oneiric.mappers;

import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.entities.DreamEntity;

public class DreamMapper {

    public static DreamDTO toDto(DreamEntity dreamEntity) {
        DreamDTO dreamDTO = new DreamDTO();
        dreamDTO.setDreamContent(dreamEntity.getDreamContent());
        dreamDTO.setDreamCategoryId(dreamEntity.getDreamCategoryId());
        dreamDTO.setDreamTitle(dreamEntity.getDreamTitle());
        dreamDTO.setDreamKind(dreamEntity.getDreamKind());
        dreamDTO.setAuthorId(dreamEntity.getAuthorId());
        dreamDTO.setCreationDate(dreamEntity.getCreationDate());
        dreamDTO.setDreamId(dreamEntity.getId());
        return dreamDTO;

    }
}
