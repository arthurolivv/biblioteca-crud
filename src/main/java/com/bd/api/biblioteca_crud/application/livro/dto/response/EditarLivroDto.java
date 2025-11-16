package com.bd.api.biblioteca_crud.application.livro.dto.response;

import com.bd.api.biblioteca_crud.application.exemplar.dto.request.CadastrarExemplarDto;
import com.bd.api.biblioteca_crud.application.exemplar.dto.request.EditarExemplarDto;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;
import com.bd.api.biblioteca_crud.domain.shared.enums.StatusExemplar;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.Year;
import java.util.List;

public record EditarLivroDto(

    @NotBlank(message = "Título é um campo obrigatório")
    String titulo,

    @NotNull
    @PastOrPresent(message = "Ano de publicação não pode ser no futuro")
    Year ano_publicacao,

    @Valid
    @NotEmpty(message = "É obrigatório informar pelo menos um autor")
    List<String> autor_oplid,

    @NotEmpty(message = "É obrigatório informar ao menos uma categoria")
    List<Long> categoria_id,

    @NotNull
    @URL(message = "A URL da imagem deve ser válida")
    String imagem_url,

    @NotNull(message = "Idioma é um campo obrigatório")
    Idioma idioma,

    @NotBlank(message = "Editora é um campo obrigatório")
    String editora_cnpj,

    @NotEmpty(message = "É obrigatório informar pelo menos um exemplar")
    List<EditarExemplarDto> exemplares,

    @NotBlank
    @Size(
            min = 50, max = 500,
            message = "A sinopse deve ter entre 50 e 500 caracteres")
    String sinopse
) {
    public EditarLivroDto(Livro livro) {

            this(
                    livro.getTitulo(),
                    livro.getAno_publicacao(),
                    livro.getAutorEscreveLivro().stream().map(ael -> ael.getAutor().getOplid()).toList(),
                    livro.getLivroPertenceCategoria().stream().map(lpc -> lpc.getCategoria().getId()).toList(),
                    livro.getImagem_url(),
                    livro.getIdioma(),
                    livro.getEditora().getCnpj(),
                    livro.getExemplares().stream().map(exe -> new EditarExemplarDto(exe.getId().getCodigo_exemplar(), exe.getProprio(), exe.getStatus())).toList(),
                    livro.getSinopse()
            );
        }
}
