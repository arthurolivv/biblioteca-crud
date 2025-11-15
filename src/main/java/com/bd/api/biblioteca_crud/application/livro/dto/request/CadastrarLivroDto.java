package com.bd.api.biblioteca_crud.application.livro.dto.request;

import com.bd.api.biblioteca_crud.application.autor.dto.request.AutorIdDto;
import com.bd.api.biblioteca_crud.application.autor.dto.request.AutorOplidDto;
import com.bd.api.biblioteca_crud.domain.categoria.Categoria;
import com.bd.api.biblioteca_crud.domain.shared.enums.Idioma;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.Year;
import java.util.List;

public record CadastrarLivroDto(

        @NotNull
        String isbn,
        @NotNull
        String titulo,
        @NotNull
        Year ano_publicacao,
        @NotNull
        List<AutorOplidDto> autor,
        @NotNull
        List<Long> categoria_id,
        @NotNull
        String imagem_url,
        @NotNull
        Idioma idioma,
        @NotNull
        String editora_cnpj
) {
}
