package com.bd.api.biblioteca_crud.application.editora.dto.response;

import com.bd.api.biblioteca_crud.domain.editora.Editora;

public record ListarEditoraDto(
        String cnpj,
        String razaoSocial,
        Integer totalLivros
) {
    public ListarEditoraDto(Editora editora) {
        this(editora.getCnpj(), editora.getRazao_social(), editora.getLivros().size());
    }
}