package com.bd.api.biblioteca_crud.application.usuario.dto.response;

import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import jakarta.validation.constraints.NotNull;

public record ListarUsuarios(

        String cpf,
        String nome,
        String sobrenome,
        String email,
        Long emprestimos_atvs,
        Long reservas_atvs,
        Boolean status

) {
    public ListarUsuarios(Usuario usuario) {

        this(
                usuario.getCpf(),
                usuario.getNome().getPri_nome(),
                usuario.getNome().getSob_nome(),
                usuario.getEmail(),
                usuario.getEmprestimos().stream()
                        .filter(e -> e.getData_devolucao() == null)
                        .count(),
                usuario.getReservas().stream().count(),
                usuario.getDeleted()
        );

    }
}
