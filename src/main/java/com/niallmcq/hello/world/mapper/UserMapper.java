package com.niallmcq.hello.world.mapper;

import com.niallmcq.hello.world.entity.User;
import com.niallmcq.hello.world.request.UserRequest;
import com.niallmcq.hello.world.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestToEntity(UserRequest createUserRequest);
    UserResponse entityToResponse(User user);
}
