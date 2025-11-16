package com.bd.api.biblioteca_crud.application.livro.validation.cadastrarLivro;

import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import org.springframework.validation.Errors;

public interface Validation<T> {

    void validate(T dto, Errors errors);
}
