package com.lms.core.mapper;

import com.lms.core.dto.UserDto;
import com.lms.core.entity.User;

public class UserMapper {

    public static UserDto mapToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());

        return dto;
    }

    public static User mapToEntity(UserDto dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        return entity;
    }

    public static User mapToEntity(UserDto userDto, User user) {
        User entity = mapToEntity(userDto);
        entity.setId(user.getId());
        return entity;
    }
}
