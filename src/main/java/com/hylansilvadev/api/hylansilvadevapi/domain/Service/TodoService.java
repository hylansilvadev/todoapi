package com.hylansilvadev.api.hylansilvadevapi.domain.Service;

import java.util.List;
import java.util.Optional;

import com.hylansilvadev.api.hylansilvadevapi.domain.DTO.TodoDTO;
import com.hylansilvadev.api.hylansilvadevapi.domain.Entity.Todo;

public interface TodoService {
    Todo save(TodoDTO data);

    Optional<Todo> findById(Long data);

    List<Todo> findAll();

    void deleteById(Long id);
}
