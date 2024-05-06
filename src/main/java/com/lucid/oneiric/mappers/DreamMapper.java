package com.lucid.oneiric.mappers;

import com.lucid.oneiric.dto.DreamDTO;
import com.lucid.oneiric.entities.DreamEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DreamMapper {


    public DreamDTO dreamEntityToDto(@NonNull Optional<DreamEntity> dreamEntityOptional) {
        try {
            DreamDTO dreamDTO = new DreamDTO();
            DreamEntity dreamEntity = dreamEntityOptional.get();
            dreamDTO.setId(dreamEntity.getId());
            dreamDTO.setDreamTitle(dreamEntity.getDreamTitle());
            dreamDTO.setDreamContent(dreamEntity.getDreamContent());
            dreamDTO.setDreamCategory(dreamEntity.getDreamCategoryEntity().getcategory());
            dreamDTO.setCreationDate(dreamEntity.getCreationDate());
            dreamDTO.setDreamKind(dreamEntity.getDreamKindEntity().getKind());
            dreamDTO.setAuthorName(dreamEntity.getUserEntity().getLogin());
            dreamDTO.setVisibilityName(dreamEntity.getVisibilityEntity().getName());

            return dreamDTO;

        } catch(Exception e) {
            e.printStackTrace();
            return null;

        }
    }
}
