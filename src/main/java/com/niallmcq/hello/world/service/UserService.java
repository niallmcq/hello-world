package com.niallmcq.hello.world.service;

import com.niallmcq.hello.world.entity.User;

import java.util.List;

public interface UserService {

    User createUser(final User user);

    List<User> getUsers();

    User getUser(final Long userId);

    void updateUser(final Long userId, final User user);

    void deleteUser(final Long userId);
}
