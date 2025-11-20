package com.bd.api.biblioteca_crud.application.usuario.dto.response;

import com.bd.api.biblioteca_crud.domain.shared.bases.Endereco;
import com.bd.api.biblioteca_crud.domain.shared.bases.Nome;
import com.bd.api.biblioteca_crud.domain.usuario.Usuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EditarUsuarioDto(

        @NotBlank(message = "RG é um campo obrigatório")
        String rg,

        @NotBlank(message = "E-mail é um campo obrigatório")
        @Email
        String email,

        // senha não está no HTML, pode remover se for editar em outra página
        String senha,

        // Nome é um objeto, deve ser @Valid ao invés de @NotBlank
        @Valid
        Nome nome,

        @Valid
        Endereco endereco,

        @NotNull
        LocalDate data_nasc
) {
    public EditarUsuarioDto(Usuario usuario) {
        this(
                usuario.getRg(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getNome(),
                usuario.getEndereco(),
                usuario.getData_nasc()
        );
    }
}


