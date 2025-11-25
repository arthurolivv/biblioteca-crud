package com.bd.api.biblioteca_crud.application.relatorio.dto.response;

public record RelatorioStatusEmprestimoDto(
        String status,
        Long totalEmprestimos
) {
}