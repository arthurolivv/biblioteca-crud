package com.bd.api.biblioteca_crud.application.usuario.dto.response;

import com.bd.api.biblioteca_crud.domain.shared.bases.CadastrarEnderecoDto;
import com.bd.api.biblioteca_crud.domain.shared.bases.CadastrarNomeDto;
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

        String senha,

        @Valid
        CadastrarNomeDto nome,

        @Valid
        CadastrarEnderecoDto endereco,

        LocalDate data_nasc
) {
    public EditarUsuarioDto(Usuario usuario) {
        this(
                usuario.getRg(),
                usuario.getEmail(),
                null,
                new CadastrarNomeDto(usuario.getNome().getPriNome(),
                        usuario.getNome().getSobNome()),
                new CadastrarEnderecoDto(usuario.getEndereco().getRua(),
                        usuario.getEndereco().getNumero(),
                        usuario.getEndereco().getComplemento(),
                        usuario.getEndereco().getBairro(),
                        usuario.getEndereco().getCidade(),
                        usuario.getEndereco().getEstado(),
                        usuario.getEndereco().getCep(),
                        usuario.getEndereco().getPaís()
                ),
                usuario.getData_nasc()
        );
    }
}


