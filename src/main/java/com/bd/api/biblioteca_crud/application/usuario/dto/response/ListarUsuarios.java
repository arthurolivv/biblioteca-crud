package com.bd.api.biblioteca_crud.application.usuario.dto.response;

import com.bd.api.biblioteca_crud.domain.shared.enums.StatusReserva;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import com.bd.api.biblioteca_crud.domain.shared.bases.Nome;

public record ListarUsuarios(

        String cpf,
        String prinome,
        String sobnome,
        String email,
        Long emprestimos_atvs,
        Long reservas_atvs,
        Boolean status

) {
    public ListarUsuarios(Usuario usuario) {

        this(
                usuario.getCpf(),
                usuario.getNome().getPriNome(),
                usuario.getNome().getSobNome(),
                usuario.getEmail(),
                usuario.getEmprestimos().stream()
                        .filter(e -> e.getData_devolucao() == null)
                        .count(),
                usuario.getReservas().stream()
                        .filter(r -> r.getStatus().equals(StatusReserva.PENDENTE))
                        .count(),
                usuario.getDeleted()
        );

    }
}
