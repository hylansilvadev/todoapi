package com.hylansilvadev.api.hylansilvadevapi.domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hylansilvadev.api.hylansilvadevapi.domain.Entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
