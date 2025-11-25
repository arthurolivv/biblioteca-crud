package com.bd.api.biblioteca_crud.application.autor.dto.request;

import com.bd.api.biblioteca_crud.domain.shared.enums.Nacionalidade;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // Importação adicionada para validação
import jakarta.validation.constraints.Size;

public record AutorCadastroDto(

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 255, message = "O nome não pode exceder 255 caracteres.")
    String nome,

    @Enumerated(EnumType.STRING)
    @NotNull(message = "A nacionalidade é obrigatória.")
    Nacionalidade nacionalidade
){
}