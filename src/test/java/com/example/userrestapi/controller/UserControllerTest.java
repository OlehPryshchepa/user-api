package com.example.userrestapi.controller;

import com.example.userrestapi.dto.request.UserRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void test_createNewUser_ok() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("test@example.com");
        userRequestDto.setFirstName("John");
        userRequestDto.setLastName("Doe");
        userRequestDto.setBirthDate(LocalDate.of(1985, 3, 22));

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void test_createNewUser_badRequest() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("test@example.com");
        userRequestDto.setFirstName("John");
        userRequestDto.setLastName("Doe");

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().json("{\"birthDate\":\"birth date can`t be null\"}"));
    }

    @Test
    public void test_updateUser() throws Exception {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setEmail("user1@example.com");
        userRequestDto.setFirstName("UpdatedFirstName");

        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                        .param("email", "user1@example.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_patchUser() throws Exception {
        Map<String, Object> updates = new HashMap<>();
        updates.put("firstName", "UpdatedFirstName");

        mockMvc.perform(MockMvcRequestBuilders.patch("/users")
                        .param("email", "user1@example.com")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updates)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/user1@example.com"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/between-dates")
                        .param("from", "1990-01-01")
                        .param("to", "1995-12-31")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
