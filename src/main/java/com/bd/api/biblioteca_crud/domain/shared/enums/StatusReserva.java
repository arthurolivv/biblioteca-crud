package com.bd.api.biblioteca_crud.domain.shared.enums;

public enum StatusReserva {

    PENDENTE,     // Reserva feita, aguardando exemplar disponível
    CANCELADA,    // Usuário cancelou ou prazo expirou
    CONCLUIDA     // Reserva atendida (livro emprestado)
}
