package com.bd.api.biblioteca_crud.application.relatorio.dto.response;

public record RelatorioAutorDto(
        String nome,
        Long totalEmprestimos
) {
}