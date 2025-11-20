package com.bd.api.biblioteca_crud.domain.shared.bases;

import jakarta.validation.constraints.NotBlank;

public record CadastrarNomeDto(

        @NotBlank(message = "Nome é um campo obrigatório")
        String pri_nome,
        @NotBlank(message = "Sobrenome é um campo obrigatório")
        String sob_nome
) {
}
