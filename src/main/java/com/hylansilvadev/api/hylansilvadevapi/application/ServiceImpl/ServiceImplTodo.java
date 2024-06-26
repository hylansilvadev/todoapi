package com.hylansilvadev.api.hylansilvadevapi.application.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hylansilvadev.api.hylansilvadevapi.domain.DTO.TodoDTO;
import com.hylansilvadev.api.hylansilvadevapi.domain.Entity.Todo;
import com.hylansilvadev.api.hylansilvadevapi.domain.Repository.TodoRepository;
import com.hylansilvadev.api.hylansilvadevapi.domain.Service.TodoService;

@Service
public class ServiceImplTodo implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo save(TodoDTO data) {
        Todo todo = new Todo(data.todoId(), data.title(), data.description(), data.isDone());
        return todoRepository.save(todo);
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}
