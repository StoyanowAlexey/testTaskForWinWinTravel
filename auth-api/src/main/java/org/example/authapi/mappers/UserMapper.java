package org.example.authapi.mappers;

import org.example.authapi.DTOs.CreateUserRequest;
import org.example.authapi.entities.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    public User toUser(CreateUserRequest createUserRequest);

}
