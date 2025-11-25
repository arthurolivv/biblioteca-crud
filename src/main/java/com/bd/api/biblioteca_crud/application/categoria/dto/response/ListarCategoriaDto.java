package com.bd.api.biblioteca_crud.application.categoria.dto.response;

public record ListarCategoriaDto(
        Long id,
        String nome,
        Long totalLivros
) {
}