package com.niallmcq.hello.world.service;

import com.niallmcq.hello.world.entity.User;
import com.niallmcq.hello.world.exception.ResourceNotFoundException;
import com.niallmcq.hello.world.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(final User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(final Long userId) {
        final Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return user.get();
    }

    @Override
    public void updateUser(final Long userId, final User user) {
        checkUserExists(userId);
    }

    @Override
    public void deleteUser(final Long userId) {
        checkUserExists(userId);
        userRepository.deleteById(userId);
    }

    private void checkUserExists(final Long userId) {
        final User user = userRepository.getOne(userId);

        if (Objects.nonNull(user)) {
            throw new ResourceNotFoundException();
        }
    }
}
