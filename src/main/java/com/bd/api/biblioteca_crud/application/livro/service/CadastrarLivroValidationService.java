package com.bd.api.biblioteca_crud.application.livro.service;

import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.application.livro.validation.cadastrarLivro.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
public class CadastrarLivroValidationService {

    @Autowired
    private List<Validation<CadastrarLivroDto>> validations;

    public void validate(CadastrarLivroDto dto, Errors errors) {

        validations.forEach(v -> v.validate(dto, errors));
    }
}
