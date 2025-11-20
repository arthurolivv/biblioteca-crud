package com.bd.api.biblioteca_crud.application.usuario.service;

import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.application.usuario.dto.request.CadastrarUsuarioDto;
import com.bd.api.biblioteca_crud.domain.shared.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
public class CadastrarUsuarioValidationService {

    @Autowired
    private List<Validation<CadastrarUsuarioDto>> validations;

    public void execute(CadastrarUsuarioDto dto, Errors errors) {

        validations.forEach(v -> v.validate(dto, errors));
    }
}
