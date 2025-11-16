package com.bd.api.biblioteca_crud.application.livro.validation.cadastrarLivro;

import com.bd.api.biblioteca_crud.application.livro.dto.request.CadastrarLivroDto;
import com.bd.api.biblioteca_crud.infraestructure.persistence.jpa.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class IsbnJaCadastradoValidation implements Validation<CadastrarLivroDto> {

    @Autowired
    private LivroRepository livroRepository;

    @Override
    public void validate(CadastrarLivroDto dto, Errors errors) {
        if (dto.isbn() != null || !dto.isbn().isBlank()){
            if (livroRepository.existsById(dto.isbn())){
                errors.rejectValue("isbn", null, "ISBN já cadastrado no sistema");
            }
        }

    }
}
