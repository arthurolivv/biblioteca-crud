package com.bd.api.biblioteca_crud.application.usuario.dto.request;

import com.bd.api.biblioteca_crud.domain.shared.bases.CadastrarEnderecoDto;
import com.bd.api.biblioteca_crud.domain.shared.bases.CadastrarNomeDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record CadastrarUsuarioDto(

        @NotBlank(message = "CPF é um campo obrigatório")
//        @CPF(message = "CPF inválido")
        String cpf,
        @NotBlank(message = "RG é um campo obrigatório")
        String rg,
        @NotNull(message = "Nome é um campo obrigatório")
        CadastrarNomeDto nome,
        @NotBlank(message = "E-mail é um campo obrigatório")
        @Email
        String email,
        @NotNull(message = "Data de Nascimento é um campo obrigatório")
        LocalDate data_nasc,
        @NotBlank(message = "Senha é um campo obrigatório")
        String senha,
        @NotNull
        CadastrarEnderecoDto endereco
) {
}
