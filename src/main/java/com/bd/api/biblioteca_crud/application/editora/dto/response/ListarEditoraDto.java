package com.bd.api.biblioteca_crud.application.editora.dto.response;

import com.bd.api.biblioteca_crud.domain.editora.Editora;

public record ListarEditoraDto(
        String cnpj,
        String razaoSocial
) {
    public ListarEditoraDto(Editora editora) {
        // Mapeia os campos da Entidade (razao_social) para o DTO (razaoSocial)
        this(editora.getCnpj(), editora.getRazao_social());
    }
}