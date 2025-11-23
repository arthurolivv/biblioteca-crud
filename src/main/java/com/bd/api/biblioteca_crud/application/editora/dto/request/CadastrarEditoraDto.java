package com.bd.api.biblioteca_crud.application.editora.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastrarEditoraDto(
        @NotBlank(message = "O CNPJ é obrigatório.")
        @Pattern(
                regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}",
                message = "O CNPJ deve estar no formato 99.999.999/9999-99."
        )
        String cnpj,

        @NotBlank(message = "A Razão Social é obrigatória.")
        @Size(max = 255, message = "A Razão Social não pode exceder 255 caracteres.")
        String razaoSocial
) {
}