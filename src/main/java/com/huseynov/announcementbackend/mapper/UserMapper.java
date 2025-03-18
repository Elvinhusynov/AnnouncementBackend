package com.huseynov.announcementbackend.mapper;

import com.huseynov.announcementbackend.dto.UserRegisterRequest;
import com.huseynov.announcementbackend.dto.UserResponse;
import com.huseynov.announcementbackend.dto.UserUpdateRequest;
import com.huseynov.announcementbackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponse toResponse(User user);

    @Mapping(target = "createdDate", expression = "java(getNow())")
    @Mapping(target = "modifiedDate", expression = "java(getNow())")
    @Mapping(target = "locked",constant = "false")
    @Mapping(target = "enabled",constant = "true")
    @Mapping(target = "role",expression = "java(com.huseynov.announcementBackend.enums.Role.USER)")

    User toUser(UserRegisterRequest request);

    @Mapping(target = "modifiedDate", expression ="java(getNow())")
    void populate(UserUpdateRequest request, @MappingTarget User user);

    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }

}
