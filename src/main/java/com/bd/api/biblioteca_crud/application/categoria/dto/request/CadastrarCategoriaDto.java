package com.bd.api.biblioteca_crud.application.categoria.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastrarCategoriaDto(
        @NotBlank(message = "O nome da categoria é obrigatório.")
        @Size(max = 100, message = "O nome da categoria não pode exceder 100 caracteres.")
        String nome
) {
}