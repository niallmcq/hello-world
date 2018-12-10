package com.niallmcq.hello.world.controller;

import com.niallmcq.hello.world.request.CreateUserRequest;
import com.niallmcq.hello.world.request.UpdateUserRequest;
import com.niallmcq.hello.world.response.UserResponse;
import com.niallmcq.hello.world.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody final CreateUserRequest createUserRequest) {
        return userService.createUser(createUserRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(@PageableDefault final Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping(value = "{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser(@PathVariable("userId") final Long userId) {
        return userService.getUser(userId);
    }

    @PutMapping(value = "{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable("userId") final Long userId, @RequestBody final UpdateUserRequest updateUserRequest) {
        userService.updateUser(userId, updateUserRequest);
    }

    @DeleteMapping(value = "{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("userId") final Long userId) {
        userService.deleteUser(userId);
    }
}
