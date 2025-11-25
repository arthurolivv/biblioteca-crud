package com.bd.api.biblioteca_crud.application.relatorio.dto.response;

public record RelatorioEditoraDto(
        String razaoSocial,
        Long totalEmprestimos
) {
}