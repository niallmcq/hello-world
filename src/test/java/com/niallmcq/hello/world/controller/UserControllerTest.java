package com.niallmcq.hello.world.controller;

import com.niallmcq.hello.world.JsonUtils;
import com.niallmcq.hello.world.request.CreateUserRequest;
import com.niallmcq.hello.world.request.UpdateUserRequest;
import com.niallmcq.hello.world.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void hitCreateEndpoint() throws Exception {
        final CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setForename("Joe");
        createUserRequest.setSurname("Bloggs");

        mockMvc.perform(
                post("/users")
                        .content(JsonUtils.writeJson(createUserRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        verify(userService).createUser(createUserRequest);
    }

    @Test
    public void hitGetAllEndpoint() throws Exception {
        mockMvc.perform(
                get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).getAllUsers(any(Pageable.class));
    }

    @Test
    public void hitGetEndpoint() throws Exception {
        final long userId = 123L;

        mockMvc.perform(
                get("/users/" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        verify(userService).getUser(userId);
    }

    @Test
    public void hitUpdateEndpoint() throws Exception {
        final long userId = 123L;

        final UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setForename("Joe");
        updateUserRequest.setSurname("Bloggs");

        mockMvc.perform(
                put("/users/" + userId)
                        .content(JsonUtils.writeJson(updateUserRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        verify(userService).updateUser(userId, updateUserRequest);
    }

    @Test
    public void hitDeleteEndpoint() throws Exception {
        final long userId = 123L;

        mockMvc.perform(
                delete("/users/" + userId))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();

        verify(userService).deleteUser(userId);
    }
}
