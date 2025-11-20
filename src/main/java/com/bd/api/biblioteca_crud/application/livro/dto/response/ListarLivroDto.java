package com.bd.api.biblioteca_crud.application.livro.dto.response;

import com.bd.api.biblioteca_crud.domain.livro.Livro;
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;

import java.time.Year;
import java.util.List;

public record ListarLivroDto(

        String isbn,
        String titulo,
        Year ano_publicacao,
        Short disponiveis,
        Short quantidade,
        String sinopse,
        Idioma idioma,
        String imagem_url,
        Boolean deleted,
        String editora,
        List<String> categorias


) {

    public ListarLivroDto(Livro livro) {
        this(
                livro.getIsbn(),
                livro.getTitulo(),
                livro.getAno_publicacao(),
                livro.getDisponiveis(),
                livro.getQuantidade(),
                livro.getSinopse(),
                livro.getIdioma(),
                livro.getImagem_url(),
                livro.isDeleted(),
                livro.getEditora().getRazao_social(),
                livro.getLivroPertenceCategoria().stream()
                        .map(c -> c.getCategoria().getNome()).
                        toList()
        );
    }

}
