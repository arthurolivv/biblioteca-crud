package com.bd.api.biblioteca_crud.model.valueobject;

public enum StatusReserva {

    PENDENTE,     // Reserva feita, aguardando exemplar disponível
    DISPONIVEL,   // Um exemplar está disponível para retirada
    CANCELADA,    // Usuário cancelou ou prazo expirou
    CONCLUIDA     // Reserva atendida (livro emprestado)
}
