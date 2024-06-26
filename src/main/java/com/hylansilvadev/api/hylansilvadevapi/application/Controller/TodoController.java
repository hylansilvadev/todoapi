package com.hylansilvadev.api.hylansilvadevapi.application.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hylansilvadev.api.hylansilvadevapi.domain.DTO.TodoDTO;
import com.hylansilvadev.api.hylansilvadevapi.domain.Entity.Todo;
import com.hylansilvadev.api.hylansilvadevapi.domain.Service.TodoService;

@RestController()
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoServiceImpl;

    // CREATE
    @PostMapping
    private ResponseEntity<Todo> createTodo(@RequestBody TodoDTO data) {
        Todo todo = todoServiceImpl.save(data);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    // READ
    @GetMapping
    private ResponseEntity<List<Todo>> findAllTodo() {
        List<Todo> todos = todoServiceImpl.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    // READ (id)
    @GetMapping("/{id}")
    private ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        Optional<Todo> todo = todoServiceImpl.findById(id);
        if (todo.isPresent()) {
            return new ResponseEntity<>(todo.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @RequestBody TodoDTO data) {
        Optional<Todo> todo = todoServiceImpl.findById(id);
        if (todo.isPresent()) {
            return new ResponseEntity<>(todo.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
