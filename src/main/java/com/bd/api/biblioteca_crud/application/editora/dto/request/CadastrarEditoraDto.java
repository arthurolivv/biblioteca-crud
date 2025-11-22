package com.bd.api.biblioteca_crud.application.editora.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CadastrarEditoraDto(
        @NotBlank(message = "O CNPJ é obrigatório.")
        @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter 14 dígitos (somente números).")
        String cnpj,

        @NotBlank(message = "A Razão Social é obrigatória.")
        @Size(max = 255, message = "A Razão Social não pode exceder 255 caracteres.")
        String razaoSocial
) {
}