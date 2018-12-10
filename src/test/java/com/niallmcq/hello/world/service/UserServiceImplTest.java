package com.niallmcq.hello.world.service;

import com.niallmcq.hello.world.entity.User;
import com.niallmcq.hello.world.mapper.UserMapper;
import com.niallmcq.hello.world.repository.UserRepository;
import com.niallmcq.hello.world.request.CreateUserRequest;
import com.niallmcq.hello.world.request.UpdateUserRequest;
import com.niallmcq.hello.world.response.UserResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl objectUnderTest;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private User user;

    private static final long userId = 1L;
    private static final String forename = "Joe";
    private static final String surname = "Bloggs";

    @Before
    public void setUp() {
        user = new User();
        user.setId(userId);
        user.setForename(forename);
        user.setSurname(surname);
    }

    @Test
    public void shouldCreateUser() {
        final CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setForename(forename);
        createUserRequest.setSurname(surname);

        final UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);
        userResponse.setForename(forename);
        userResponse.setSurname(surname);

        when(userMapper.requestToEntity(createUserRequest)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.entityToResponse(user)).thenReturn(userResponse);

        final UserResponse result = objectUnderTest.createUser(createUserRequest);
    }

    @Ignore
    @Test
    public void shouldGetAllUsers() {
        final UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);
        userResponse.setForename(forename);
        userResponse.setSurname(surname);

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        when(userMapper.entityToResponse(user)).thenReturn(userResponse);

        final List<UserResponse> result = objectUnderTest.getAllUsers(PageRequest.of(0, 10));
    }

    @Test
    public void shouldGetUser() {
        final UserResponse userResponse = new UserResponse();
        userResponse.setId(userId);
        userResponse.setForename(forename);
        userResponse.setSurname(surname);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.entityToResponse(user)).thenReturn(userResponse);

        final UserResponse result = objectUnderTest.getUser(userId);
    }

    @Test
    public void shouldUpdateUser() {
        final UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setForename(forename);
        updateUserRequest.setSurname(surname);

        when(userRepository.getOne(userId)).thenReturn(user);
        when(userMapper.requestToEntity(updateUserRequest)).thenReturn(user);

        objectUnderTest.updateUser(userId, updateUserRequest);

        verify(userRepository).save(user);
    }

    @Test
    public void shouldDeleteUser() {
        when(userRepository.getOne(userId)).thenReturn(user);
        doNothing().when(userRepository).deleteById(userId);

        objectUnderTest.deleteUser(userId);

        verify(userRepository).deleteById(userId);
    }
}
