package com.hylansilvadev.api.hylansilvadevapi.application.Controller;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hylansilvadev.api.hylansilvadevapi.domain.DTO.TodoDTO;
import com.hylansilvadev.api.hylansilvadevapi.domain.Entity.Todo;
import com.hylansilvadev.api.hylansilvadevapi.domain.Service.TodoService;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Autowired
    private ObjectMapper objectMapper;

    private TodoDTO todoDTO;
    private Todo expectedSavedTodo;

    @BeforeEach
    public void setup() {
        todoDTO = new TodoDTO(null, "Task Title", "Description of the task", false);
        expectedSavedTodo = new Todo(1L, "Task Title", "Description of the task", false);
    }

    @Test
    @DisplayName("Create a TODO successfully")
    public void testCreateTodo() throws Exception {
        given(todoService.save(any(TodoDTO.class))).willReturn(expectedSavedTodo);

        mockMvc.perform(post("/api/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.todoId").value(expectedSavedTodo.getTodoId()))
                .andExpect(jsonPath("$.title").value(expectedSavedTodo.getTitle()))
                .andExpect(jsonPath("$.description").value(expectedSavedTodo.getDescription()))
                .andExpect(jsonPath("$.isDone").value(expectedSavedTodo.isDone()));
    }

    @Test
    @DisplayName("Find all TODOs")
    public void testFindAllTodo() throws Exception {
        given(todoService.findAll()).willReturn(Arrays.asList(expectedSavedTodo));

        mockMvc.perform(get("/api/todo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(expectedSavedTodo.getTitle()))
                .andExpect(jsonPath("$[0].description").value(expectedSavedTodo.getDescription()))
                .andExpect(jsonPath("$[0].isDone").value(expectedSavedTodo.isDone()));
    }

    @Test
    @DisplayName("Get a TODO by ID")
    public void testGetTodoById() throws Exception {
        given(todoService.findById(1L)).willReturn(Optional.of(expectedSavedTodo));

        mockMvc.perform(get("/api/todo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(expectedSavedTodo.getTitle()))
                .andExpect(jsonPath("$.description").value(expectedSavedTodo.getDescription()))
                .andExpect(jsonPath("$.isDone").value(expectedSavedTodo.isDone()));
    }

    @Test
    @DisplayName("Get a TODO by ID - Not Found")
    public void testGetTodoById_NotFound() throws Exception {
        given(todoService.findById(1L)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/todo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
