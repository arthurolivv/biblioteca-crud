package com.bd.api.biblioteca_crud.application.livro.dto.request;

import com.bd.api.biblioteca_crud.application.autor.dto.request.AutorOplidDto;
import com.bd.api.biblioteca_crud.application.exemplar.dto.request.CadastrarExemplarDto;
import com.bd.api.biblioteca_crud.domain.exemplar.Exemplar;
import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import java.time.Year;
import java.util.List;

public record CadastrarLivroDto(

        @NotBlank(message = "ISBN é um campo obrigatório")
        @Pattern(
                regexp = "^(?:\\d{9}[\\dX]|\\d{13})$",
                message = "ISBN deve ser válido no formato  ISBN-10 ou ISBN-13 caracteres"
        )
        String isbn,

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
//        @Pattern(
//                regexp = "\\d{2}\\.\\d{3}\\.\\d{3}\\-\\d{4}\\-\\d{2}",
//                message = "CNPJ deve seguir o seguinte formato: 00.000.000/0000-00"
//        )
//        @CNPJ(message = "CNPJ inválido")
        String editora_cnpj,

        @NotEmpty(message = "É obrigatório informar pelo menos um exemplar")
        List<CadastrarExemplarDto> exemplares,

        @NotBlank
        @Size(
                min = 50, max = 500,
                message = "A sinopse deve ter entre 50 e 500 caracteres")
        String sinopse
) {
    public CadastrarLivroDto(Livro livro) {

        this(
                livro.getIsbn(),
                livro.getTitulo(),
                livro.getAno_publicacao(),
                livro.getAutorEscreveLivro().stream().map(ael -> ael.getAutor().getOplid()).toList(),
                livro.getLivroPertenceCategoria().stream().map(lpc -> lpc.getCategoria().getId()).toList(),
                livro.getImagem_url(),
                livro.getIdioma(),
                livro.getEditora().getCnpj(),
                livro.getExemplares().stream().map(exe -> new CadastrarExemplarDto(exe.getId().getCodigo_exemplar(), exe.getProprio())).toList(),
                livro.getSinopse()
        );
    }
}
