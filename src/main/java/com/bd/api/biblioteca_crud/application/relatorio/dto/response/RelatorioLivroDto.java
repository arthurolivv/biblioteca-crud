package com.bd.api.biblioteca_crud.application.relatorio.dto.response;

public record RelatorioLivroDto(
        String titulo,
        Long totalEmprestimos
) {
}