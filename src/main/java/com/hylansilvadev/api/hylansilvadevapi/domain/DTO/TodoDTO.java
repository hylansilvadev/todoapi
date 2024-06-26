package com.hylansilvadev.api.hylansilvadevapi.domain.DTO;

public record TodoDTO(
    Long todoId,
    String title,
    String description,
    boolean isDone
) {}
