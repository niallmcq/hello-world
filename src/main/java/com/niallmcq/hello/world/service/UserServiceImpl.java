package com.niallmcq.hello.world.service;

import com.niallmcq.hello.world.entity.User;
import com.niallmcq.hello.world.exception.ResourceNotFoundException;
import com.niallmcq.hello.world.mapper.UserMapper;
import com.niallmcq.hello.world.repository.UserRepository;
import com.niallmcq.hello.world.request.CreateUserRequest;
import com.niallmcq.hello.world.request.UpdateUserRequest;
import com.niallmcq.hello.world.request.UserRequest;
import com.niallmcq.hello.world.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponse createUser(final CreateUserRequest createUserRequest) {
        final User user = userRepository.save(convertToEntity(createUserRequest));
        return convertToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers(final Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(final Long userId) {
        final Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return convertToResponse(user.get());
    }

    @Override
    public void updateUser(final Long userId, final UpdateUserRequest updateUserRequest) {
        checkUserExists(userId);
        final User user = convertToEntity(updateUserRequest);
        user.setId(userId);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(final Long userId) {
        checkUserExists(userId);
        userRepository.deleteById(userId);
    }

    private void checkUserExists(final Long userId) {
        final User user = userRepository.getOne(userId);

        if (Objects.isNull(user)) {
            throw new ResourceNotFoundException();
        }
    }

    private User convertToEntity(final UserRequest userRequest) {
        return userMapper.requestToEntity(userRequest);
    }

    private UserResponse convertToResponse(final User user) {
        return userMapper.entityToResponse(user);
    }
}
