package com.niallmcq.hello.world.service;

import com.niallmcq.hello.world.request.CreateUserRequest;
import com.niallmcq.hello.world.request.UpdateUserRequest;
import com.niallmcq.hello.world.response.UserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest createUserRequest);

    List<UserResponse> getAllUsers(Pageable pageable);

    UserResponse getUser(Long userId);

    void updateUser(Long userId, UpdateUserRequest updateUserRequest);

    void deleteUser(Long userId);
}
