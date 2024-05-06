package com.lucid.oneiric.mappers;

import com.lucid.oneiric.dto.UserPublicViewDTO;
import com.lucid.oneiric.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class PublicUserMapper {

    public UserPublicViewDTO mapUserDataToPublicViewDTO(UserEntity userEntity) {
        UserPublicViewDTO userPublicViewDTO = new UserPublicViewDTO();
        userPublicViewDTO.setLogin(userEntity.getLogin());
        userPublicViewDTO.setLucidDreamCount(userEntity.getLucidDreamCount());
        userPublicViewDTO.setRegularDreamCount(userEntity.getRegularDreamCount());
        return userPublicViewDTO;

    }
}
